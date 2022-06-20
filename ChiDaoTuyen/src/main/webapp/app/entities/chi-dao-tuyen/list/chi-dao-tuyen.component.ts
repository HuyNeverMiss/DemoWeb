import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IChiDaoTuyen } from '../chi-dao-tuyen.model';
import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';
import { ChiDaoTuyenDeleteDialogComponent } from '../delete/chi-dao-tuyen-delete-dialog.component';

@Component({
  selector: 'jhi-chi-dao-tuyen',
  templateUrl: './chi-dao-tuyen.component.html',
})
export class ChiDaoTuyenComponent implements OnInit {
  chiDaoTuyens?: IChiDaoTuyen[];
  isLoading = false;

  constructor(protected chiDaoTuyenService: ChiDaoTuyenService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.chiDaoTuyenService.query().subscribe({
      next: (res: HttpResponse<IChiDaoTuyen[]>) => {
        this.isLoading = false;
        this.chiDaoTuyens = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IChiDaoTuyen): number {
    return item.id!;
  }

  delete(chiDaoTuyen: IChiDaoTuyen): void {
    const modalRef = this.modalService.open(ChiDaoTuyenDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.chiDaoTuyen = chiDaoTuyen;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
