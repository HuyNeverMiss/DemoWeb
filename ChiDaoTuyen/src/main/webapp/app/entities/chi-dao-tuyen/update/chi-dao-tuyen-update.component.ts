import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IChiDaoTuyen, ChiDaoTuyen } from '../chi-dao-tuyen.model';
import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';
import { ILyDoCongTac } from 'app/entities/ly-do-cong-tac/ly-do-cong-tac.model';
import { LyDoCongTacService } from 'app/entities/ly-do-cong-tac/service/ly-do-cong-tac.service';
import { INoiDenCongTac } from 'app/entities/noi-den-cong-tac/noi-den-cong-tac.model';
import { NoiDenCongTacService } from 'app/entities/noi-den-cong-tac/service/noi-den-cong-tac.service';
import { IKetQuaCongTac } from 'app/entities/ket-qua-cong-tac/ket-qua-cong-tac.model';
import { KetQuaCongTacService } from 'app/entities/ket-qua-cong-tac/service/ket-qua-cong-tac.service';
import { IKyThuatHoTro } from 'app/entities/ky-thuat-ho-tro/ky-thuat-ho-tro.model';
import { KyThuatHoTroService } from 'app/entities/ky-thuat-ho-tro/service/ky-thuat-ho-tro.service';
import { IVatTuHoTro } from 'app/entities/vat-tu-ho-tro/vat-tu-ho-tro.model';
import { VatTuHoTroService } from 'app/entities/vat-tu-ho-tro/service/vat-tu-ho-tro.service';
import { INhanVien } from 'app/entities/nhan-vien/nhan-vien.model';
import { NhanVienService } from 'app/entities/nhan-vien/service/nhan-vien.service';

@Component({
  selector: 'jhi-chi-dao-tuyen-update',
  templateUrl: './chi-dao-tuyen-update.component.html',
})
export class ChiDaoTuyenUpdateComponent implements OnInit {
  isSaving = false;

  lyDoCongTacsSharedCollection: ILyDoCongTac[] = [];
  noiDenCongTacsSharedCollection: INoiDenCongTac[] = [];
  ketQuaCongTacsSharedCollection: IKetQuaCongTac[] = [];
  kyThuatHoTrosSharedCollection: IKyThuatHoTro[] = [];
  vatTuHoTrosSharedCollection: IVatTuHoTro[] = [];
  nhanViensSharedCollection: INhanVien[] = [];

  editForm = this.fb.group({
    id: [],
    soQuyetDinh: [null, [Validators.required]],
    ngayQuyetDinh: [null, [Validators.required]],
    soHD: [null, [Validators.required]],
    ngayHD: [null, [Validators.required]],
    noiDung: [null, [Validators.required]],
    ngayBatDau: [null, [Validators.required]],
    ngayKetThuc: [null, [Validators.required]],
    ghiChu: [],
    ngayTao: [null, [Validators.required]],
    soBnKhamDieuTri: [],
    soBnPhauThuat: [],
    soCanBoChuyenGiao: [],
    luuTru: [],
    tienAn: [],
    tienO: [],
    tienDiLai: [],
    taiLieu: [],
    giangDay: [],
    khac: [],
    lyDoCongTac: [],
    noiDenCongTac: [],
    ketQuaCongTac: [],
    kyThuatHoTro: [],
    vatTuHoTro: [],
    nhanVien: [],
  });

  constructor(
    protected chiDaoTuyenService: ChiDaoTuyenService,
    protected lyDoCongTacService: LyDoCongTacService,
    protected noiDenCongTacService: NoiDenCongTacService,
    protected ketQuaCongTacService: KetQuaCongTacService,
    protected kyThuatHoTroService: KyThuatHoTroService,
    protected vatTuHoTroService: VatTuHoTroService,
    protected nhanVienService: NhanVienService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

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

      this.loadRelationshipsOptions();
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

  trackLyDoCongTacById(_index: number, item: ILyDoCongTac): number {
    return item.id!;
  }

  trackNoiDenCongTacById(_index: number, item: INoiDenCongTac): number {
    return item.id!;
  }

  trackKetQuaCongTacById(_index: number, item: IKetQuaCongTac): number {
    return item.id!;
  }

  trackKyThuatHoTroById(_index: number, item: IKyThuatHoTro): number {
    return item.id!;
  }

  trackVatTuHoTroById(_index: number, item: IVatTuHoTro): number {
    return item.id!;
  }

  trackNhanVienById(_index: number, item: INhanVien): number {
    return item.id!;
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
      noiDung: chiDaoTuyen.noiDung,
      ngayBatDau: chiDaoTuyen.ngayBatDau ? chiDaoTuyen.ngayBatDau.format(DATE_TIME_FORMAT) : null,
      ngayKetThuc: chiDaoTuyen.ngayKetThuc ? chiDaoTuyen.ngayKetThuc.format(DATE_TIME_FORMAT) : null,
      ghiChu: chiDaoTuyen.ghiChu,
      ngayTao: chiDaoTuyen.ngayTao ? chiDaoTuyen.ngayTao.format(DATE_TIME_FORMAT) : null,
      soBnKhamDieuTri: chiDaoTuyen.soBnKhamDieuTri,
      soBnPhauThuat: chiDaoTuyen.soBnPhauThuat,
      soCanBoChuyenGiao: chiDaoTuyen.soCanBoChuyenGiao,
      luuTru: chiDaoTuyen.luuTru,
      tienAn: chiDaoTuyen.tienAn,
      tienO: chiDaoTuyen.tienO,
      tienDiLai: chiDaoTuyen.tienDiLai,
      taiLieu: chiDaoTuyen.taiLieu,
      giangDay: chiDaoTuyen.giangDay,
      khac: chiDaoTuyen.khac,
      lyDoCongTac: chiDaoTuyen.lyDoCongTac,
      noiDenCongTac: chiDaoTuyen.noiDenCongTac,
      ketQuaCongTac: chiDaoTuyen.ketQuaCongTac,
      kyThuatHoTro: chiDaoTuyen.kyThuatHoTro,
      vatTuHoTro: chiDaoTuyen.vatTuHoTro,
      nhanVien: chiDaoTuyen.nhanVien,
    });

    this.lyDoCongTacsSharedCollection = this.lyDoCongTacService.addLyDoCongTacToCollectionIfMissing(
      this.lyDoCongTacsSharedCollection,
      chiDaoTuyen.lyDoCongTac
    );
    this.noiDenCongTacsSharedCollection = this.noiDenCongTacService.addNoiDenCongTacToCollectionIfMissing(
      this.noiDenCongTacsSharedCollection,
      chiDaoTuyen.noiDenCongTac
    );
    this.ketQuaCongTacsSharedCollection = this.ketQuaCongTacService.addKetQuaCongTacToCollectionIfMissing(
      this.ketQuaCongTacsSharedCollection,
      chiDaoTuyen.ketQuaCongTac
    );
    this.kyThuatHoTrosSharedCollection = this.kyThuatHoTroService.addKyThuatHoTroToCollectionIfMissing(
      this.kyThuatHoTrosSharedCollection,
      chiDaoTuyen.kyThuatHoTro
    );
    this.vatTuHoTrosSharedCollection = this.vatTuHoTroService.addVatTuHoTroToCollectionIfMissing(
      this.vatTuHoTrosSharedCollection,
      chiDaoTuyen.vatTuHoTro
    );
    this.nhanViensSharedCollection = this.nhanVienService.addNhanVienToCollectionIfMissing(
      this.nhanViensSharedCollection,
      chiDaoTuyen.nhanVien
    );
  }

  protected loadRelationshipsOptions(): void {
    this.lyDoCongTacService
      .query()
      .pipe(map((res: HttpResponse<ILyDoCongTac[]>) => res.body ?? []))
      .pipe(
        map((lyDoCongTacs: ILyDoCongTac[]) =>
          this.lyDoCongTacService.addLyDoCongTacToCollectionIfMissing(lyDoCongTacs, this.editForm.get('lyDoCongTac')!.value)
        )
      )
      .subscribe((lyDoCongTacs: ILyDoCongTac[]) => (this.lyDoCongTacsSharedCollection = lyDoCongTacs));

    this.noiDenCongTacService
      .query()
      .pipe(map((res: HttpResponse<INoiDenCongTac[]>) => res.body ?? []))
      .pipe(
        map((noiDenCongTacs: INoiDenCongTac[]) =>
          this.noiDenCongTacService.addNoiDenCongTacToCollectionIfMissing(noiDenCongTacs, this.editForm.get('noiDenCongTac')!.value)
        )
      )
      .subscribe((noiDenCongTacs: INoiDenCongTac[]) => (this.noiDenCongTacsSharedCollection = noiDenCongTacs));

    this.ketQuaCongTacService
      .query()
      .pipe(map((res: HttpResponse<IKetQuaCongTac[]>) => res.body ?? []))
      .pipe(
        map((ketQuaCongTacs: IKetQuaCongTac[]) =>
          this.ketQuaCongTacService.addKetQuaCongTacToCollectionIfMissing(ketQuaCongTacs, this.editForm.get('ketQuaCongTac')!.value)
        )
      )
      .subscribe((ketQuaCongTacs: IKetQuaCongTac[]) => (this.ketQuaCongTacsSharedCollection = ketQuaCongTacs));

    this.kyThuatHoTroService
      .query()
      .pipe(map((res: HttpResponse<IKyThuatHoTro[]>) => res.body ?? []))
      .pipe(
        map((kyThuatHoTros: IKyThuatHoTro[]) =>
          this.kyThuatHoTroService.addKyThuatHoTroToCollectionIfMissing(kyThuatHoTros, this.editForm.get('kyThuatHoTro')!.value)
        )
      )
      .subscribe((kyThuatHoTros: IKyThuatHoTro[]) => (this.kyThuatHoTrosSharedCollection = kyThuatHoTros));

    this.vatTuHoTroService
      .query()
      .pipe(map((res: HttpResponse<IVatTuHoTro[]>) => res.body ?? []))
      .pipe(
        map((vatTuHoTros: IVatTuHoTro[]) =>
          this.vatTuHoTroService.addVatTuHoTroToCollectionIfMissing(vatTuHoTros, this.editForm.get('vatTuHoTro')!.value)
        )
      )
      .subscribe((vatTuHoTros: IVatTuHoTro[]) => (this.vatTuHoTrosSharedCollection = vatTuHoTros));

    this.nhanVienService
      .query()
      .pipe(map((res: HttpResponse<INhanVien[]>) => res.body ?? []))
      .pipe(
        map((nhanViens: INhanVien[]) =>
          this.nhanVienService.addNhanVienToCollectionIfMissing(nhanViens, this.editForm.get('nhanVien')!.value)
        )
      )
      .subscribe((nhanViens: INhanVien[]) => (this.nhanViensSharedCollection = nhanViens));
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
      noiDung: this.editForm.get(['noiDung'])!.value,
      ngayBatDau: this.editForm.get(['ngayBatDau'])!.value ? dayjs(this.editForm.get(['ngayBatDau'])!.value, DATE_TIME_FORMAT) : undefined,
      ngayKetThuc: this.editForm.get(['ngayKetThuc'])!.value
        ? dayjs(this.editForm.get(['ngayKetThuc'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ghiChu: this.editForm.get(['ghiChu'])!.value,
      ngayTao: this.editForm.get(['ngayTao'])!.value ? dayjs(this.editForm.get(['ngayTao'])!.value, DATE_TIME_FORMAT) : undefined,
      soBnKhamDieuTri: this.editForm.get(['soBnKhamDieuTri'])!.value,
      soBnPhauThuat: this.editForm.get(['soBnPhauThuat'])!.value,
      soCanBoChuyenGiao: this.editForm.get(['soCanBoChuyenGiao'])!.value,
      luuTru: this.editForm.get(['luuTru'])!.value,
      tienAn: this.editForm.get(['tienAn'])!.value,
      tienO: this.editForm.get(['tienO'])!.value,
      tienDiLai: this.editForm.get(['tienDiLai'])!.value,
      taiLieu: this.editForm.get(['taiLieu'])!.value,
      giangDay: this.editForm.get(['giangDay'])!.value,
      khac: this.editForm.get(['khac'])!.value,
      lyDoCongTac: this.editForm.get(['lyDoCongTac'])!.value,
      noiDenCongTac: this.editForm.get(['noiDenCongTac'])!.value,
      ketQuaCongTac: this.editForm.get(['ketQuaCongTac'])!.value,
      kyThuatHoTro: this.editForm.get(['kyThuatHoTro'])!.value,
      vatTuHoTro: this.editForm.get(['vatTuHoTro'])!.value,
      nhanVien: this.editForm.get(['nhanVien'])!.value,
    };
  }
}
