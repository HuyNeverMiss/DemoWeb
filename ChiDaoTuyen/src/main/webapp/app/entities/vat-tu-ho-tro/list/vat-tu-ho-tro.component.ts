import { IVatTuHoTro, VatTuHoTro } from './../vat-tu-ho-tro.model';
import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { Observable } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/config/pagination.constants';
import { VatTuHoTroService } from '../service/vat-tu-ho-tro.service';
import { VatTuHoTroDeleteDialogComponent } from '../delete/vat-tu-ho-tro-delete-dialog.component';

import { FormBuilder, Validators } from '@angular/forms';
import { finalize, map } from 'rxjs/operators';

@Component({
  selector: 'jhi-vat-tu-ho-tro',
  templateUrl: './vat-tu-ho-tro.component.html',
})
export class VatTuHoTroComponent implements OnInit {
  vatTuHoTros?: IVatTuHoTro[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  ids?: number;
  maVatTus?: string;
  tenVatTus?: string;
  thuTuSXs?: string;
  isSaving = false;
  id1: any;
  maVatTu1 = '';
  tenVatTu1 = '';
  thuTuSX1 = '';
  maVT = '';
  tenVT = '';
  ttsx = '';

  editForm = this.fb.group({
    id: [],
    maVatTu: [null, [Validators.required]],
    tenVatTu: [null, [Validators.required]],
    thuTuSX: [],
  });

  constructor(
    protected vatTuHoTroService: VatTuHoTroService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected fb: FormBuilder
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.vatTuHoTroService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<IVatTuHoTro[]>) => {
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

  trackId(_index: number, item: IVatTuHoTro): number {
    return item.id!;
  }

  delete(): void {
    const deleteVatTuHoTro = this.createFromForm();
    deleteVatTuHoTro.id = this.ids;
    deleteVatTuHoTro.maVatTu = this.maVatTus;
    deleteVatTuHoTro.tenVatTu = this.tenVatTus;
    deleteVatTuHoTro.thuTuSX = this.thuTuSXs;
    const modalRef = this.modalService.open(VatTuHoTroDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.vatTuHoTro = deleteVatTuHoTro;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }
    });
    this.ids = undefined;
    this.maVatTus = '';
    this.tenVatTus = '';
    this.thuTuSXs = '';
  }

  showInfor(id?: number, maVatTu?: string, tenVatTu?: string, thuTuSX?: string): void {
    // eslint-disable-next-line no-console
    this.ids = id;
    this.maVatTus = maVatTu;
    this.tenVatTus = tenVatTu;
    this.thuTuSXs = thuTuSX;
  }

  create(): void {
    this.isSaving = true;
    const createVatTuHoTro = this.createFromForm();
    this.subscribeToSaveResponse(this.vatTuHoTroService.create(createVatTuHoTro));
    this.ids = undefined;
    this.maVatTus = '';
    this.tenVatTus = '';
    this.thuTuSXs = '';
  }
  cancel(): void {
    this.ids = undefined;
    this.maVatTus = '';
    this.tenVatTus = '';
    this.thuTuSXs = '';
  }
  save(): void {
    this.isSaving = true;
    const vatTuHoTro = this.createFromForm();
    vatTuHoTro.id = this.id1 || this.ids;
    vatTuHoTro.maVatTu = this.maVatTu1 || this.maVatTus;
    vatTuHoTro.tenVatTu = this.tenVatTu1 || this.tenVatTus;
    vatTuHoTro.thuTuSX = this.thuTuSX1 || this.thuTuSXs;
    // eslint-disable-next-line no-console
    this.subscribeToSaveResponse(this.vatTuHoTroService.update(vatTuHoTro));
    this.ids = undefined;
    this.maVatTus = '';
    this.tenVatTus = '';
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

  protected onSuccess(data: IVatTuHoTro[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/vat-tu-ho-tro'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? ASC : DESC),
        },
      });
    }
    this.vatTuHoTros = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVatTuHoTro>>): void {
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

  protected updateForm(vatTuHoTro: IVatTuHoTro): void {
    this.editForm.patchValue({
      id: vatTuHoTro.id,
      maVatTu: vatTuHoTro.maVatTu,
      tenVatTu: vatTuHoTro.tenVatTu,
      thuTuSX: vatTuHoTro.thuTuSX,
    });
  }

  protected createFromForm(): IVatTuHoTro {
    return {
      ...new VatTuHoTro(),
      id: this.editForm.get(['id'])!.value,
      maVatTu: this.editForm.get(['maVatTu'])!.value,
      tenVatTu: this.editForm.get(['tenVatTu'])!.value,
      thuTuSX: this.editForm.get(['thuTuSX'])!.value,
    };
  }
}
