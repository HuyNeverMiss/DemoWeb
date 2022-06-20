import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INoiDenCongTac } from '../noi-den-cong-tac.model';
import { NoiDenCongTacService } from '../service/noi-den-cong-tac.service';
import { NoiDenCongTacDeleteDialogComponent } from '../delete/noi-den-cong-tac-delete-dialog.component';

@Component({
  selector: 'jhi-noi-den-cong-tac',
  templateUrl: './noi-den-cong-tac.component.html',
})
export class NoiDenCongTacComponent implements OnInit {
  noiDenCongTacs?: INoiDenCongTac[];
  isLoading = false;

  constructor(protected noiDenCongTacService: NoiDenCongTacService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.noiDenCongTacService.query().subscribe({
      next: (res: HttpResponse<INoiDenCongTac[]>) => {
        this.isLoading = false;
        this.noiDenCongTacs = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: INoiDenCongTac): number {
    return item.id!;
  }

  delete(noiDenCongTac: INoiDenCongTac): void {
    const modalRef = this.modalService.open(NoiDenCongTacDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.noiDenCongTac = noiDenCongTac;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
