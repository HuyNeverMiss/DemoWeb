import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IChiDaoTuyen, ChiDaoTuyen } from '../chi-dao-tuyen.model';
import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';

@Component({
  selector: 'jhi-chi-dao-tuyen-update',
  templateUrl: './chi-dao-tuyen-update.component.html',
})
export class ChiDaoTuyenUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    soQuyetDinh: [],
    ngayQuyetDinh: [],
    soHD: [],
    ngayHD: [],
    lyDoCT: [],
    noiDung: [],
    noiCongTac: [],
    ngayBatDau: [],
    ngayKetThuc: [],
    ghiChu: [],
    ngayTao: [],
    nhanVien: [],
    kyThuatHoTro: [],
    vatTuHoTro: [],
    soBnKhamDieuTri: [],
    soBnPhauThuat: [],
    soCanBoChuyenGiao: [],
    ketQuaCongTac: [],
    luuTru: [],
    tienAn: [],
    tienO: [],
    tienDiLai: [],
    taiLieu: [],
    giangDay: [],
    khac: [],
  });

  constructor(protected chiDaoTuyenService: ChiDaoTuyenService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chiDaoTuyen }) => {
      if (chiDaoTuyen.id === undefined) {
        const today = dayjs().startOf('day');
        chiDaoTuyen.ngayQuyetDinh = today;
        chiDaoTuyen.ngayHD = today;
        chiDaoTuyen.ngayBatDau = today;
        chiDaoTuyen.ngayKetThuc = today;
        chiDaoTuyen.ngayTao = today;
      }

      this.updateForm(chiDaoTuyen);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const chiDaoTuyen = this.createFromForm();
    if (chiDaoTuyen.id !== undefined) {
      this.subscribeToSaveResponse(this.chiDaoTuyenService.update(chiDaoTuyen));
    } else {
      this.subscribeToSaveResponse(this.chiDaoTuyenService.create(chiDaoTuyen));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChiDaoTuyen>>): void {
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

  protected updateForm(chiDaoTuyen: IChiDaoTuyen): void {
    this.editForm.patchValue({
      id: chiDaoTuyen.id,
      soQuyetDinh: chiDaoTuyen.soQuyetDinh,
      ngayQuyetDinh: chiDaoTuyen.ngayQuyetDinh ? chiDaoTuyen.ngayQuyetDinh.format(DATE_TIME_FORMAT) : null,
      soHD: chiDaoTuyen.soHD,
      ngayHD: chiDaoTuyen.ngayHD ? chiDaoTuyen.ngayHD.format(DATE_TIME_FORMAT) : null,
      lyDoCT: chiDaoTuyen.lyDoCT,
      noiDung: chiDaoTuyen.noiDung,
      noiCongTac: chiDaoTuyen.noiCongTac,
      ngayBatDau: chiDaoTuyen.ngayBatDau ? chiDaoTuyen.ngayBatDau.format(DATE_TIME_FORMAT) : null,
      ngayKetThuc: chiDaoTuyen.ngayKetThuc ? chiDaoTuyen.ngayKetThuc.format(DATE_TIME_FORMAT) : null,
      ghiChu: chiDaoTuyen.ghiChu,
      ngayTao: chiDaoTuyen.ngayTao ? chiDaoTuyen.ngayTao.format(DATE_TIME_FORMAT) : null,
      nhanVien: chiDaoTuyen.nhanVien,
      kyThuatHoTro: chiDaoTuyen.kyThuatHoTro,
      vatTuHoTro: chiDaoTuyen.vatTuHoTro,
      soBnKhamDieuTri: chiDaoTuyen.soBnKhamDieuTri,
      soBnPhauThuat: chiDaoTuyen.soBnPhauThuat,
      soCanBoChuyenGiao: chiDaoTuyen.soCanBoChuyenGiao,
      ketQuaCongTac: chiDaoTuyen.ketQuaCongTac,
      luuTru: chiDaoTuyen.luuTru,
      tienAn: chiDaoTuyen.tienAn,
      tienO: chiDaoTuyen.tienO,
      tienDiLai: chiDaoTuyen.tienDiLai,
      taiLieu: chiDaoTuyen.taiLieu,
      giangDay: chiDaoTuyen.giangDay,
      khac: chiDaoTuyen.khac,
    });
  }

  protected createFromForm(): IChiDaoTuyen {
    return {
      ...new ChiDaoTuyen(),
      id: this.editForm.get(['id'])!.value,
      soQuyetDinh: this.editForm.get(['soQuyetDinh'])!.value,
      ngayQuyetDinh: this.editForm.get(['ngayQuyetDinh'])!.value
        ? dayjs(this.editForm.get(['ngayQuyetDinh'])!.value, DATE_TIME_FORMAT)
        : undefined,
      soHD: this.editForm.get(['soHD'])!.value,
      ngayHD: this.editForm.get(['ngayHD'])!.value ? dayjs(this.editForm.get(['ngayHD'])!.value, DATE_TIME_FORMAT) : undefined,
      lyDoCT: this.editForm.get(['lyDoCT'])!.value,
      noiDung: this.editForm.get(['noiDung'])!.value,
      noiCongTac: this.editForm.get(['noiCongTac'])!.value,
      ngayBatDau: this.editForm.get(['ngayBatDau'])!.value ? dayjs(this.editForm.get(['ngayBatDau'])!.value, DATE_TIME_FORMAT) : undefined,
      ngayKetThuc: this.editForm.get(['ngayKetThuc'])!.value
        ? dayjs(this.editForm.get(['ngayKetThuc'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ghiChu: this.editForm.get(['ghiChu'])!.value,
      ngayTao: this.editForm.get(['ngayTao'])!.value ? dayjs(this.editForm.get(['ngayTao'])!.value, DATE_TIME_FORMAT) : undefined,
      nhanVien: this.editForm.get(['nhanVien'])!.value,
      kyThuatHoTro: this.editForm.get(['kyThuatHoTro'])!.value,
      vatTuHoTro: this.editForm.get(['vatTuHoTro'])!.value,
      soBnKhamDieuTri: this.editForm.get(['soBnKhamDieuTri'])!.value,
      soBnPhauThuat: this.editForm.get(['soBnPhauThuat'])!.value,
      soCanBoChuyenGiao: this.editForm.get(['soCanBoChuyenGiao'])!.value,
      ketQuaCongTac: this.editForm.get(['ketQuaCongTac'])!.value,
      luuTru: this.editForm.get(['luuTru'])!.value,
      tienAn: this.editForm.get(['tienAn'])!.value,
      tienO: this.editForm.get(['tienO'])!.value,
      tienDiLai: this.editForm.get(['tienDiLai'])!.value,
      taiLieu: this.editForm.get(['taiLieu'])!.value,
      giangDay: this.editForm.get(['giangDay'])!.value,
      khac: this.editForm.get(['khac'])!.value,
    };
  }
}
