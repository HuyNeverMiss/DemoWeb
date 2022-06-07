import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IHoTro, HoTro } from '../ho-tro.model';
import { HoTroService } from '../service/ho-tro.service';
import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';
import { ChiDaoTuyenService } from 'app/entities/chi-dao-tuyen/service/chi-dao-tuyen.service';

@Component({
  selector: 'jhi-ho-tro-update',
  templateUrl: './ho-tro-update.component.html',
})
export class HoTroUpdateComponent implements OnInit {
  isSaving = false;

  chiDaoTuyensSharedCollection: IChiDaoTuyen[] = [];

  editForm = this.fb.group({
    id: [],
    maNoiDung: [],
    ngayTao: [],
    nhanVienCV: [],
    kTHoTro: [],
    vatTuHoTro: [],
    soBnKhamDieuTri: [],
    soBnPhauThuat: [],
    soCanBoChuyenGiao: [],
    danhGiaKQCongTac: [],
    chiDaoTuyen: [],
  });

  constructor(
    protected hoTroService: HoTroService,
    protected chiDaoTuyenService: ChiDaoTuyenService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hoTro }) => {
      if (hoTro.id === undefined) {
        const today = dayjs().startOf('day');
        hoTro.ngayTao = today;
      }

      this.updateForm(hoTro);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hoTro = this.createFromForm();
    if (hoTro.id !== undefined) {
      this.subscribeToSaveResponse(this.hoTroService.update(hoTro));
    } else {
      this.subscribeToSaveResponse(this.hoTroService.create(hoTro));
    }
  }

  trackChiDaoTuyenById(_index: number, item: IChiDaoTuyen): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHoTro>>): void {
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

  protected updateForm(hoTro: IHoTro): void {
    this.editForm.patchValue({
      id: hoTro.id,
      maNoiDung: hoTro.maNoiDung,
      ngayTao: hoTro.ngayTao ? hoTro.ngayTao.format(DATE_TIME_FORMAT) : null,
      nhanVienCV: hoTro.nhanVienCV,
      kTHoTro: hoTro.kTHoTro,
      vatTuHoTro: hoTro.vatTuHoTro,
      soBnKhamDieuTri: hoTro.soBnKhamDieuTri,
      soBnPhauThuat: hoTro.soBnPhauThuat,
      soCanBoChuyenGiao: hoTro.soCanBoChuyenGiao,
      danhGiaKQCongTac: hoTro.danhGiaKQCongTac,
      chiDaoTuyen: hoTro.chiDaoTuyen,
    });

    this.chiDaoTuyensSharedCollection = this.chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing(
      this.chiDaoTuyensSharedCollection,
      hoTro.chiDaoTuyen
    );
  }

  protected loadRelationshipsOptions(): void {
    this.chiDaoTuyenService
      .query()
      .pipe(map((res: HttpResponse<IChiDaoTuyen[]>) => res.body ?? []))
      .pipe(
        map((chiDaoTuyens: IChiDaoTuyen[]) =>
          this.chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing(chiDaoTuyens, this.editForm.get('chiDaoTuyen')!.value)
        )
      )
      .subscribe((chiDaoTuyens: IChiDaoTuyen[]) => (this.chiDaoTuyensSharedCollection = chiDaoTuyens));
  }

  protected createFromForm(): IHoTro {
    return {
      ...new HoTro(),
      id: this.editForm.get(['id'])!.value,
      maNoiDung: this.editForm.get(['maNoiDung'])!.value,
      ngayTao: this.editForm.get(['ngayTao'])!.value ? dayjs(this.editForm.get(['ngayTao'])!.value, DATE_TIME_FORMAT) : undefined,
      nhanVienCV: this.editForm.get(['nhanVienCV'])!.value,
      kTHoTro: this.editForm.get(['kTHoTro'])!.value,
      vatTuHoTro: this.editForm.get(['vatTuHoTro'])!.value,
      soBnKhamDieuTri: this.editForm.get(['soBnKhamDieuTri'])!.value,
      soBnPhauThuat: this.editForm.get(['soBnPhauThuat'])!.value,
      soCanBoChuyenGiao: this.editForm.get(['soCanBoChuyenGiao'])!.value,
      danhGiaKQCongTac: this.editForm.get(['danhGiaKQCongTac'])!.value,
      chiDaoTuyen: this.editForm.get(['chiDaoTuyen'])!.value,
    };
  }
}
