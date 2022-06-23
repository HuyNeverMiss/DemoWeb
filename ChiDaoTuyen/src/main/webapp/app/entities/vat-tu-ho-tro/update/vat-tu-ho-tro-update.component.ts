import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IVatTuHoTro, VatTuHoTro } from '../vat-tu-ho-tro.model';
import { VatTuHoTroService } from '../service/vat-tu-ho-tro.service';

@Component({
  selector: 'jhi-vat-tu-ho-tro-update',
  templateUrl: './vat-tu-ho-tro-update.component.html',
})
export class VatTuHoTroUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    maVatTu: [null, [Validators.required]],
    tenVatTu: [null, [Validators.required]],
    thuTuSX: [],
  });

  constructor(protected vatTuHoTroService: VatTuHoTroService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vatTuHoTro }) => {
      this.updateForm(vatTuHoTro);
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
