import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IVatTuHoTro, VatTuHoTro } from '../vat-tu-ho-tro.model';
import { VatTuHoTroService } from '../service/vat-tu-ho-tro.service';
import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';
import { ChiDaoTuyenService } from 'app/entities/chi-dao-tuyen/service/chi-dao-tuyen.service';

@Component({
  selector: 'jhi-vat-tu-ho-tro-update',
  templateUrl: './vat-tu-ho-tro-update.component.html',
})
export class VatTuHoTroUpdateComponent implements OnInit {
  isSaving = false;

  chiDaoTuyensCollection: IChiDaoTuyen[] = [];

  editForm = this.fb.group({
    id: [],
    maVatTu: [],
    tenVatTu: [],
    thuTuSX: [],
    chiDaoTuyen: [],
  });

  constructor(
    protected vatTuHoTroService: VatTuHoTroService,
    protected chiDaoTuyenService: ChiDaoTuyenService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vatTuHoTro }) => {
      this.updateForm(vatTuHoTro);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vatTuHoTro = this.createFromForm();
    if (vatTuHoTro.id !== undefined) {
      this.subscribeToSaveResponse(this.vatTuHoTroService.update(vatTuHoTro));
    } else {
      this.subscribeToSaveResponse(this.vatTuHoTroService.create(vatTuHoTro));
    }
  }

  trackChiDaoTuyenById(_index: number, item: IChiDaoTuyen): number {
    return item.id!;
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
      chiDaoTuyen: vatTuHoTro.chiDaoTuyen,
    });

    this.chiDaoTuyensCollection = this.chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing(
      this.chiDaoTuyensCollection,
      vatTuHoTro.chiDaoTuyen
    );
  }

  protected loadRelationshipsOptions(): void {
    this.chiDaoTuyenService
      .query({ 'vatTuHoTroId.specified': 'false' })
      .pipe(map((res: HttpResponse<IChiDaoTuyen[]>) => res.body ?? []))
      .pipe(
        map((chiDaoTuyens: IChiDaoTuyen[]) =>
          this.chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing(chiDaoTuyens, this.editForm.get('chiDaoTuyen')!.value)
        )
      )
      .subscribe((chiDaoTuyens: IChiDaoTuyen[]) => (this.chiDaoTuyensCollection = chiDaoTuyens));
  }

  protected createFromForm(): IVatTuHoTro {
    return {
      ...new VatTuHoTro(),
      id: this.editForm.get(['id'])!.value,
      maVatTu: this.editForm.get(['maVatTu'])!.value,
      tenVatTu: this.editForm.get(['tenVatTu'])!.value,
      thuTuSX: this.editForm.get(['thuTuSX'])!.value,
      chiDaoTuyen: this.editForm.get(['chiDaoTuyen'])!.value,
    };
  }
}
