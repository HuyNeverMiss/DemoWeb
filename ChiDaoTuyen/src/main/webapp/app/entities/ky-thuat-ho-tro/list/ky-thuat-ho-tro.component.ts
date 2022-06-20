import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IKyThuatHoTro } from '../ky-thuat-ho-tro.model';
import { KyThuatHoTroService } from '../service/ky-thuat-ho-tro.service';
import { KyThuatHoTroDeleteDialogComponent } from '../delete/ky-thuat-ho-tro-delete-dialog.component';

@Component({
  selector: 'jhi-ky-thuat-ho-tro',
  templateUrl: './ky-thuat-ho-tro.component.html',
})
export class KyThuatHoTroComponent implements OnInit {
  kyThuatHoTros?: IKyThuatHoTro[];
  isLoading = false;

  constructor(protected kyThuatHoTroService: KyThuatHoTroService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.kyThuatHoTroService.query().subscribe({
      next: (res: HttpResponse<IKyThuatHoTro[]>) => {
        this.isLoading = false;
        this.kyThuatHoTros = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IKyThuatHoTro): number {
    return item.id!;
  }

  delete(kyThuatHoTro: IKyThuatHoTro): void {
    const modalRef = this.modalService.open(KyThuatHoTroDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.kyThuatHoTro = kyThuatHoTro;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
