import { INoiDenCongTac, NoiDenCongTac } from './../noi-den-cong-tac.model';
import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { Observable } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/config/pagination.constants';
import { NoiDenCongTacService } from '../service/noi-den-cong-tac.service';
import { NoiDenCongTacDeleteDialogComponent } from '../delete/noi-den-cong-tac-delete-dialog.component';

import { FormBuilder, Validators } from '@angular/forms';
import { finalize, map } from 'rxjs/operators';
@Component({
  selector: 'jhi-noi-den-cong-tac',
  templateUrl: './noi-den-cong-tac.component.html',
})
export class NoiDenCongTacComponent implements OnInit {
  noiDenCongTacs?: INoiDenCongTac[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  ids?: number;
  maNoiDens?: string;
  tenNoiDens?: string;
  thuTuSXs?: string;
  isSaving = false;
  id1: any;
  maNoiDen1 = '';
  tenNoiDen1 = '';
  thuTuSX1 = '';
  maND = '';
  tenND = '';
  ttsx = '';

  editForm = this.fb.group({
    id: [],
    maNoiDen: [null, [Validators.required]],
    tenNoiDen: [null, [Validators.required]],
    thuTuSX: [],
  });

  constructor(
    protected noiDenCongTacService: NoiDenCongTacService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected fb: FormBuilder
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.noiDenCongTacService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<INoiDenCongTac[]>) => {
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

  trackId(_index: number, item: INoiDenCongTac): number {
    return item.id!;
  }

  delete(): void {
    const deleteNoiDenCongTac = this.createFromForm();
    deleteNoiDenCongTac.id = this.ids;
    deleteNoiDenCongTac.maNoiDen = this.maNoiDens;
    deleteNoiDenCongTac.tenNoiDen = this.tenNoiDens;
    deleteNoiDenCongTac.thuTuSX = this.thuTuSXs;
    const modalRef = this.modalService.open(NoiDenCongTacDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.noiDenCongTac = deleteNoiDenCongTac;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }
    });
    this.ids = undefined;
    this.maNoiDens = '';
    this.tenNoiDens = '';
    this.thuTuSXs = '';
  }

  showInfor(id?: number, maNoiDen?: string, tenNoiDen?: string, thuTuSX?: string): void {
    // eslint-disable-next-line no-console
    this.ids = id;
    this.maNoiDens = maNoiDen;
    this.tenNoiDens = tenNoiDen;
    this.thuTuSXs = thuTuSX;
  }

  create(): void {
    this.isSaving = true;
    const createNoiDenCongTac = this.createFromForm();
    this.subscribeToSaveResponse(this.noiDenCongTacService.create(createNoiDenCongTac));
    this.ids = undefined;
    this.maNoiDens = '';
    this.tenNoiDens = '';
    this.thuTuSXs = '';
  }
  cancel(): void {
    this.ids = undefined;
    this.maNoiDens = '';
    this.tenNoiDens = '';
    this.thuTuSXs = '';
  }
  save(): void {
    this.isSaving = true;
    const noiDenCongTac = this.createFromForm();
    noiDenCongTac.id = this.id1 || this.ids;
    noiDenCongTac.maNoiDen = this.maNoiDen1 || this.maNoiDens;
    noiDenCongTac.tenNoiDen = this.tenNoiDen1 || this.tenNoiDens;
    noiDenCongTac.thuTuSX = this.thuTuSX1 || this.thuTuSXs;
    // eslint-disable-next-line no-console
    this.subscribeToSaveResponse(this.noiDenCongTacService.update(noiDenCongTac));
    this.ids = undefined;
    this.maNoiDens = '';
    this.tenNoiDens = '';
    this.thuTuSXs = '';
  }

  previousState(): void {
    this.loadPage();
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

  protected onSuccess(data: INoiDenCongTac[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/noi-den-cong-tac'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? ASC : DESC),
        },
      });
    }
    this.noiDenCongTacs = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INoiDenCongTac>>): void {
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

  protected updateForm(noiDenCongTac: INoiDenCongTac): void {
    this.editForm.patchValue({
      id: noiDenCongTac.id,
      maNoiDen: noiDenCongTac.maNoiDen,
      tenNoiDen: noiDenCongTac.tenNoiDen,
      thuTuSX: noiDenCongTac.thuTuSX,
    });
  }

  protected createFromForm(): INoiDenCongTac {
    return {
      ...new NoiDenCongTac(),
      id: this.editForm.get(['id'])!.value,
      maNoiDen: this.editForm.get(['maNoiDen'])!.value,
      tenNoiDen: this.editForm.get(['tenNoiDen'])!.value,
      thuTuSX: this.editForm.get(['thuTuSX'])!.value,
    };
  }
}
