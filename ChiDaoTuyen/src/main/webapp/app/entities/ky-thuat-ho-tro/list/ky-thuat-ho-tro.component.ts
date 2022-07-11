import { IKyThuatHoTro, KyThuatHoTro } from './../ky-thuat-ho-tro.model';
import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { Observable } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/config/pagination.constants';
import { KyThuatHoTroService } from '../service/ky-thuat-ho-tro.service';
import { KyThuatHoTroDeleteDialogComponent } from '../delete/ky-thuat-ho-tro-delete-dialog.component';

import { FormBuilder, Validators } from '@angular/forms';
import { finalize, map } from 'rxjs/operators';
@Component({
  selector: 'jhi-ky-thuat-ho-tro',
  templateUrl: './ky-thuat-ho-tro.component.html',
})
export class KyThuatHoTroComponent implements OnInit {
  kyThuatHoTros?: IKyThuatHoTro[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  ids?: number;
  maKyThuats?: string;
  tenKyThuats?: string;
  thuTuSXs?: string;
  isSaving = false;
  id1: any;
  maKyThuat1 = '';
  tenKyThuat1 = '';
  thuTuSX1 = '';
  maKT = '';
  tenKT = '';
  ttsx = '';

  editForm = this.fb.group({
    id: [],
    maKyThuat: [null, [Validators.required]],
    tenKyThuat: [null, [Validators.required]],
    thuTuSX: [],
  });

  constructor(
    protected kyThuatHoTroService: KyThuatHoTroService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected fb: FormBuilder
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.kyThuatHoTroService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<IKyThuatHoTro[]>) => {
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

  trackId(_index: number, item: IKyThuatHoTro): number {
    return item.id!;
  }

  delete(): void {
    const deleteKyThuatHoTro = this.createFromForm();
    deleteKyThuatHoTro.id = this.ids;
    deleteKyThuatHoTro.maKyThuat = this.maKyThuats;
    deleteKyThuatHoTro.tenKyThuat = this.tenKyThuats;
    deleteKyThuatHoTro.thuTuSX = this.thuTuSXs;
    const modalRef = this.modalService.open(KyThuatHoTroDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.kyThuatHoTro = deleteKyThuatHoTro;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }
    });
    this.ids = undefined;
    this.maKyThuats = '';
    this.tenKyThuats = '';
    this.thuTuSXs = '';
  }

  showInfor(id?: number, maKyThuat?: string, tenKyThuat?: string, thuTuSX?: string): void {
    // eslint-disable-next-line no-console
    this.ids = id;
    this.maKyThuats = maKyThuat;
    this.tenKyThuats = tenKyThuat;
    this.thuTuSXs = thuTuSX;
  }

  create(): void {
    this.isSaving = true;
    const createKyThuatHoTro = this.createFromForm();
    this.subscribeToSaveResponse(this.kyThuatHoTroService.create(createKyThuatHoTro));
    this.ids = undefined;
    this.maKyThuats = '';
    this.tenKyThuats = '';
    this.thuTuSXs = '';
  }
  cancel(): void {
    this.ids = undefined;
    this.maKyThuats = '';
    this.tenKyThuats = '';
    this.thuTuSXs = '';
  }
  save(): void {
    this.isSaving = true;
    const kyThuatHoTro = this.createFromForm();
    kyThuatHoTro.id = this.id1 || this.ids;
    kyThuatHoTro.maKyThuat = this.maKyThuat1 || this.maKyThuats;
    kyThuatHoTro.tenKyThuat = this.tenKyThuat1 || this.tenKyThuats;
    kyThuatHoTro.thuTuSX = this.thuTuSX1 || this.thuTuSXs;
    // eslint-disable-next-line no-console
    this.subscribeToSaveResponse(this.kyThuatHoTroService.update(kyThuatHoTro));
    this.ids = undefined;
    this.maKyThuats = '';
    this.tenKyThuats = '';
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

  protected onSuccess(data: IKyThuatHoTro[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/ky-thuat-ho-tro'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? ASC : DESC),
        },
      });
    }
    this.kyThuatHoTros = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKyThuatHoTro>>): void {
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

  protected updateForm(kyThuatHoTro: IKyThuatHoTro): void {
    this.editForm.patchValue({
      id: kyThuatHoTro.id,
      maKyThuat: kyThuatHoTro.maKyThuat,
      tenKyThuat: kyThuatHoTro.tenKyThuat,
      thuTuSX: kyThuatHoTro.thuTuSX,
    });
  }

  protected createFromForm(): IKyThuatHoTro {
    return {
      ...new KyThuatHoTro(),
      id: this.editForm.get(['id'])!.value,
      maKyThuat: this.editForm.get(['maKyThuat'])!.value,
      tenKyThuat: this.editForm.get(['tenKyThuat'])!.value,
      thuTuSX: this.editForm.get(['thuTuSX'])!.value,
    };
  }
}
