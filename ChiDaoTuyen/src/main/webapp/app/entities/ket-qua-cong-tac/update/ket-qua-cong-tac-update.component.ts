import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IKetQuaCongTac, KetQuaCongTac } from '../ket-qua-cong-tac.model';
import { KetQuaCongTacService } from '../service/ket-qua-cong-tac.service';

@Component({
  selector: 'jhi-ket-qua-cong-tac-update',
  templateUrl: './ket-qua-cong-tac-update.component.html',
})
export class KetQuaCongTacUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    maKetQua: [null, [Validators.required]],
    tenKetQua: [null, [Validators.required]],
    thuTuSX: [],
  });

  constructor(protected ketQuaCongTacService: KetQuaCongTacService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ketQuaCongTac }) => {
      this.updateForm(ketQuaCongTac);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ketQuaCongTac = this.createFromForm();
    if (ketQuaCongTac.id !== undefined) {
      this.subscribeToSaveResponse(this.ketQuaCongTacService.update(ketQuaCongTac));
    } else {
      this.subscribeToSaveResponse(this.ketQuaCongTacService.create(ketQuaCongTac));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKetQuaCongTac>>): void {
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

  protected updateForm(ketQuaCongTac: IKetQuaCongTac): void {
    this.editForm.patchValue({
      id: ketQuaCongTac.id,
      maKetQua: ketQuaCongTac.maKetQua,
      tenKetQua: ketQuaCongTac.tenKetQua,
      thuTuSX: ketQuaCongTac.thuTuSX,
    });
  }

  protected createFromForm(): IKetQuaCongTac {
    return {
      ...new KetQuaCongTac(),
      id: this.editForm.get(['id'])!.value,
      maKetQua: this.editForm.get(['maKetQua'])!.value,
      tenKetQua: this.editForm.get(['tenKetQua'])!.value,
      thuTuSX: this.editForm.get(['thuTuSX'])!.value,
    };
  }
}
