import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVatTuHoTro } from '../vat-tu-ho-tro.model';
import { VatTuHoTroService } from '../service/vat-tu-ho-tro.service';
import { VatTuHoTroDeleteDialogComponent } from '../delete/vat-tu-ho-tro-delete-dialog.component';

@Component({
  selector: 'jhi-vat-tu-ho-tro',
  templateUrl: './vat-tu-ho-tro.component.html',
})
export class VatTuHoTroComponent implements OnInit {
  vatTuHoTros?: IVatTuHoTro[];
  isLoading = false;

  constructor(protected vatTuHoTroService: VatTuHoTroService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.vatTuHoTroService.query().subscribe({
      next: (res: HttpResponse<IVatTuHoTro[]>) => {
        this.isLoading = false;
        this.vatTuHoTros = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IVatTuHoTro): number {
    return item.id!;
  }

  delete(vatTuHoTro: IVatTuHoTro): void {
    const modalRef = this.modalService.open(VatTuHoTroDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.vatTuHoTro = vatTuHoTro;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
