import { INhanVien, NhanVien } from './../nhan-vien.model';
import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { Observable } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/config/pagination.constants';
import { NhanVienService } from '../service/nhan-vien.service';
import { NhanVienDeleteDialogComponent } from '../delete/nhan-vien-delete-dialog.component';

import { FormBuilder, Validators } from '@angular/forms';
import { finalize, map } from 'rxjs/operators';

@Component({
  selector: 'jhi-nhan-vien',
  templateUrl: './nhan-vien.component.html',
})
export class NhanVienComponent implements OnInit {
  nhanViens?: INhanVien[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  ids?: number;
  maNhanViens?: string;
  chucVus?: string;
  isSaving = false;
  id1: any;
  maNhanVien1 = '';
  chucVu1 = '';
  maNV = '';
  cv = '';

  editForm = this.fb.group({
    id: [],
    maNhanVien: [null, [Validators.required]],
    chucVu: [],
  });


  constructor(
    protected nhanVienService: NhanVienService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
    protected fb: FormBuilder
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.nhanVienService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<INhanVien[]>) => {
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

  trackId(_index: number, item: INhanVien): number {
    return item.id!;
  }

  delete(): void {
    const deleteNhanVien = this.createFromForm();
    deleteNhanVien.id = this.ids;
    deleteNhanVien.maNhanVien = this.maNhanViens;
    deleteNhanVien.chucVu = this.chucVus;
    const modalRef = this.modalService.open(NhanVienDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.nhanVien = deleteNhanVien;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }
    });
    this.ids = undefined;
    this.maNhanViens = '';
    this.chucVus = '';
  }

  showInfor(id?: number, maNhanVien?: string, chucVu?: string): void {
    // eslint-disable-next-line no-console
    this.ids = id;
    this.maNhanViens = maNhanVien;
    this.chucVus = chucVu;
  }

  create(): void {
    this.isSaving = true;
    const createNhanVien = this.createFromForm();
    this.subscribeToSaveResponse(this.nhanVienService.create(createNhanVien));
    this.ids = undefined;
    this.maNhanViens = '';
    this.chucVus = '';
  }
  cancel(): void {
    this.ids = undefined;
    this.maNhanViens = '';
    this.chucVus = '';
  }
  save(): void {
    this.isSaving = true;
    const nhanVien = this.createFromForm();
    nhanVien.id = this.id1 || this.ids;
    nhanVien.maNhanVien = this.maNhanVien1 || this.maNhanViens;
    nhanVien.chucVu = this.chucVu1 || this.chucVus;
    // eslint-disable-next-line no-console
    this.subscribeToSaveResponse(this.nhanVienService.update(nhanVien));
    this.ids = undefined;
    this.maNhanViens = '';
    this.chucVus = '';
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

  protected onSuccess(data: INhanVien[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/nhan-vien'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? ASC : DESC),
        },
      });
    }
    this.nhanViens = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INhanVien>>): void {
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

  protected updateForm(nhanVien: INhanVien): void {
    this.editForm.patchValue({
      id: nhanVien.id,
      maNhanVien: nhanVien.maNhanVien,
      chucVu: nhanVien.chucVu,
    });
  }

  protected createFromForm(): INhanVien {
    return {
      ...new NhanVien(),
      id: this.editForm.get(['id'])!.value,
      maNhanVien: this.editForm.get(['maNhanVien'])!.value,
      chucVu: this.editForm.get(['chucVu'])!.value,
    };
  }
}
