import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILyDoCongTac } from '../ly-do-cong-tac.model';
import { LyDoCongTacService } from '../service/ly-do-cong-tac.service';
import { LyDoCongTacDeleteDialogComponent } from '../delete/ly-do-cong-tac-delete-dialog.component';

@Component({
  selector: 'jhi-ly-do-cong-tac',
  templateUrl: './ly-do-cong-tac.component.html',
})
export class LyDoCongTacComponent implements OnInit {
  lyDoCongTacs?: ILyDoCongTac[];
  isLoading = false;

  constructor(protected lyDoCongTacService: LyDoCongTacService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.lyDoCongTacService.query().subscribe({
      next: (res: HttpResponse<ILyDoCongTac[]>) => {
        this.isLoading = false;
        this.lyDoCongTacs = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: ILyDoCongTac): number {
    return item.id!;
  }

  delete(lyDoCongTac: ILyDoCongTac): void {
    const modalRef = this.modalService.open(LyDoCongTacDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.lyDoCongTac = lyDoCongTac;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
