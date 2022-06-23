import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { INoiDenCongTac, NoiDenCongTac } from '../noi-den-cong-tac.model';
import { NoiDenCongTacService } from '../service/noi-den-cong-tac.service';

@Component({
  selector: 'jhi-noi-den-cong-tac-update',
  templateUrl: './noi-den-cong-tac-update.component.html',
})
export class NoiDenCongTacUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    maNoiDen: [null, [Validators.required]],
    tenNoiDen: [null, [Validators.required]],
    thuTuSX: [],
  });

  constructor(protected noiDenCongTacService: NoiDenCongTacService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ noiDenCongTac }) => {
      this.updateForm(noiDenCongTac);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const noiDenCongTac = this.createFromForm();
    if (noiDenCongTac.id !== undefined) {
      this.subscribeToSaveResponse(this.noiDenCongTacService.update(noiDenCongTac));
    } else {
      this.subscribeToSaveResponse(this.noiDenCongTacService.create(noiDenCongTac));
    }
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
