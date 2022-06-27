import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { Observable } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILyDoCongTac, LyDoCongTac } from '../ly-do-cong-tac.model';

import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/config/pagination.constants';
import { LyDoCongTacService } from '../service/ly-do-cong-tac.service';
import { LyDoCongTacDeleteDialogComponent } from '../delete/ly-do-cong-tac-delete-dialog.component';




import { FormBuilder } from '@angular/forms';
import { finalize, map } from 'rxjs/operators';
import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';
import { ChiDaoTuyenService } from 'app/entities/chi-dao-tuyen/service/chi-dao-tuyen.service';
import { NULL_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'jhi-ly-do-cong-tac',
  templateUrl: './ly-do-cong-tac.component.html',
})
export class LyDoCongTacComponent implements OnInit {
  lyDoCongTacs?: ILyDoCongTac[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  ids?: number;
  maLyDos?: string;
  tenLyDos?: string;
  thuTuSXs?: string;
  isSaving = false;
  id1 :any;
  maLyDo1 = '';
  tenLyDo1 = '';
  thuTuSX1 = '';

  editForm = this.fb.group({
    id: [],
    maLyDo: [],
    tenLyDo: [],
    thuTuSX: [],
    chiDaoTuyen: [],
  });

  constructor(
    protected lyDoCongTacService: LyDoCongTacService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected fb: FormBuilder
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.lyDoCongTacService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<ILyDoCongTac[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        error: () => {
          this.isLoading = false;
          this.onError();
        },
      });
  }

  ngOnInit(): void {
    this.handleNavigation();
  }
  previousState(): void {
    this.loadPage();
  }

  trackId(_index: number, item: ILyDoCongTac): number {
    return item.id!;
  }

  delete(): void {
    const deleteLyDoCongTac = this.createFromForm();
    deleteLyDoCongTac.id = this.ids;
    deleteLyDoCongTac.maLyDo = this.maLyDos;
    deleteLyDoCongTac.tenLyDo = this.tenLyDos;
    deleteLyDoCongTac.thuTuSX = this.thuTuSXs;
    const modalRef = this.modalService.open(LyDoCongTacDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.lyDoCongTac = deleteLyDoCongTac;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }

    });
    this.ids = undefined;
    this.maLyDos = '';
    this.tenLyDos = '';
    this.thuTuSXs = '';
  }

  showInfor(id?: number, maLyDo?: string, tenLyDo?: string, thuTuSX?: string): void{
    // eslint-disable-next-line no-console
    this.ids = id ;
    this.maLyDos=maLyDo;
    this.tenLyDos = tenLyDo;
    this.thuTuSXs = thuTuSX;
  }

  create(): void{
    this.isSaving = true;
    const createLyDoCongTac = this.createFromForm();
    this.subscribeToSaveResponse(this.lyDoCongTacService.create(createLyDoCongTac))
    this.ids = undefined;
    this.maLyDos = '';
    this.tenLyDos = '';
    this.thuTuSXs = '';
  }
  cancel(): void{
    this.ids = undefined;
    this.maLyDos = '';
    this.tenLyDos = '';
    this.thuTuSXs = '';

  }
  save(): void {
    this.isSaving = true;
    const lyDoCongTac = this.createFromForm();
    lyDoCongTac.id = this.id1 || this.ids;
    lyDoCongTac.maLyDo = this.maLyDo1 || this.maLyDos;
    lyDoCongTac.tenLyDo = this.tenLyDo1 || this.tenLyDos;
    lyDoCongTac.thuTuSX = this.thuTuSX1 || this.thuTuSXs;
    // eslint-disable-next-line no-console
    console.log(lyDoCongTac);
    this.subscribeToSaveResponse(this.lyDoCongTacService.update(lyDoCongTac))
    this.ids = undefined;
    this.maLyDos = '';
    this.tenLyDos = '';
    this.thuTuSXs = '';
  }
  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILyDoCongTac>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }
  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? ASC : DESC)];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).subscribe(([data, params]) => {
      const page = params.get('page');
      const pageNumber = +(page ?? 1);
      const sort = (params.get(SORT) ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === ASC;
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    });
  }

  protected onSuccess(data: ILyDoCongTac[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/ly-do-cong-tac'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? ASC : DESC),
        },
      });
    }
    this.lyDoCongTacs = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  protected createFromForm(): ILyDoCongTac {
    return {
      ...new LyDoCongTac(),
      id: this.editForm.get(['id'])!.value,
      maLyDo: this.editForm.get(['maLyDo'])!.value,
      tenLyDo: this.editForm.get(['tenLyDo'])!.value,
      thuTuSX: this.editForm.get(['thuTuSX'])!.value,
    };
  }
}

