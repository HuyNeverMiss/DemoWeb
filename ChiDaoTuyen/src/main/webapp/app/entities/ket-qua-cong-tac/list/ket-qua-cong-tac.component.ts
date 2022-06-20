import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IKetQuaCongTac } from '../ket-qua-cong-tac.model';
import { KetQuaCongTacService } from '../service/ket-qua-cong-tac.service';
import { KetQuaCongTacDeleteDialogComponent } from '../delete/ket-qua-cong-tac-delete-dialog.component';

@Component({
  selector: 'jhi-ket-qua-cong-tac',
  templateUrl: './ket-qua-cong-tac.component.html',
})
export class KetQuaCongTacComponent implements OnInit {
  ketQuaCongTacs?: IKetQuaCongTac[];
  isLoading = false;

  constructor(protected ketQuaCongTacService: KetQuaCongTacService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ketQuaCongTacService.query().subscribe({
      next: (res: HttpResponse<IKetQuaCongTac[]>) => {
        this.isLoading = false;
        this.ketQuaCongTacs = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IKetQuaCongTac): number {
    return item.id!;
  }

  delete(ketQuaCongTac: IKetQuaCongTac): void {
    const modalRef = this.modalService.open(KetQuaCongTacDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ketQuaCongTac = ketQuaCongTac;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
