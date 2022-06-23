import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IKyThuatHoTro, KyThuatHoTro } from '../ky-thuat-ho-tro.model';
import { KyThuatHoTroService } from '../service/ky-thuat-ho-tro.service';

@Component({
  selector: 'jhi-ky-thuat-ho-tro-update',
  templateUrl: './ky-thuat-ho-tro-update.component.html',
})
export class KyThuatHoTroUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    maKyThuat: [null, [Validators.required]],
    tenKyThuat: [null, [Validators.required]],
    thuTuSX: [],
  });

  constructor(protected kyThuatHoTroService: KyThuatHoTroService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ kyThuatHoTro }) => {
      this.updateForm(kyThuatHoTro);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const kyThuatHoTro = this.createFromForm();
    if (kyThuatHoTro.id !== undefined) {
      this.subscribeToSaveResponse(this.kyThuatHoTroService.update(kyThuatHoTro));
    } else {
      this.subscribeToSaveResponse(this.kyThuatHoTroService.create(kyThuatHoTro));
    }
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
