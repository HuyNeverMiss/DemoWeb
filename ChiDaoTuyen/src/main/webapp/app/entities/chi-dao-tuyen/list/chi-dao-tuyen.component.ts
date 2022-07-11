import { VatTuHoTro } from './../../vat-tu-ho-tro/vat-tu-ho-tro.model';
import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ChiDaoTuyen, IChiDaoTuyen } from '../chi-dao-tuyen.model';

import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/config/pagination.constants';
import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';
import { ChiDaoTuyenDeleteDialogComponent } from '../delete/chi-dao-tuyen-delete-dialog.component';

import { FormBuilder, Validators } from '@angular/forms';

import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { DATE_FORMAT } from 'app/config/input.constants';
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
  selector: 'jhi-chi-dao-tuyen',
  templateUrl: './chi-dao-tuyen.component.html',
})
export class ChiDaoTuyenComponent implements OnInit {
  chiDaoTuyens?: IChiDaoTuyen[];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
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
  vatTuHoTros: number | undefined;
  lyDoCongTacs: number | undefined;
  noiDenCongTacs: number | undefined;
  ketQuaCongTacs: number | undefined;
  kyThuatHoTros: number | undefined;
  nhanViens: number | undefined;

  ids?: number;
  soQuyetDinhs?: string;
  ngayQuyetDinhs? = dayjs(this.editForm.get(['ngayQuyetDinh'])!.value, DATE_FORMAT);
  soHDs?: string;
  ngayHDs? = dayjs(this.editForm.get(['ngayHD'])!.value, DATE_FORMAT);
  noiDungs?: string;
  ngayBatDaus? = dayjs(this.editForm.get(['ngayBatDau'])!.value, DATE_FORMAT);
  ngayKetThucs ?= dayjs(this.editForm.get(['ngayKetThuc'])!.value, DATE_FORMAT);
  ghiChus?: string;
  ngayTaos ?= dayjs(this.editForm.get(['ngayTao'])!.value, DATE_FORMAT);
  soBnKhamDieuTris?: string;
  soBnPhauThuats?: string;
  soCanBoChuyenGiaos?: string;
  luuTrus?: string;
  tienAns?: string;
  tienOs?: string;
  tienDiLais?: string;
  taiLieus?: string;
  giangDays?: string;
  khacs?: string;
  lyDoCongTac = this.editForm.get(['lyDoCongTac'])!.value;
  noiDenCongTac = this.editForm.get(['noiDenCongTac'])!.value;
  ketQuaCongTac = this.editForm.get(['ketQuaCongTac'])!.value;
  kyThuatHoTro = this.editForm.get(['kyThuatHoTro'])!.value;
  nhanVien = this.editForm.get(['nhanVien'])!.value;
  vatTuHoTro = this.editForm.get(['vatTuHoTro'])!.value;

  id1: any;
  soQuyetDinh1 = '';
  ngayQuyetDinh1 = '';
  soHD1 = '';
  ngayHD1 = '';
  noiDung1 = '';
  ngayBatDau1 = '';
  ngayKetThuc1 = '';
  ghiChu1 = '';
  ngayTao1 = '';
  soBnKhamDieuTri1 = '';
  soBnPhauThuat1 = '';
  soCanBoChuyenGiao1 = '';
  luuTru1 = '';
  tienAn1 = '';
  tienO1 = '';
  tienDiLai1 = '';
  taiLieu1 = '';
  giangDay1 = '';
  khac1 = '';
  lyDoCongTac1 = this.editForm.get(['lyDoCongTac'])!.value;
  noiDenCongTac1 = this.editForm.get(['noiDenCongTac'])!.value;
  ketQuaCongTac1 = this.editForm.get(['ketQuaCongTac'])!.value;
  kyThuatHoTro1 = this.editForm.get(['kyThuatHoTro'])!.value;
  vatTuHoTro1 = this.editForm.get(['vatTuHoTro'])!.value;
  nhanVien1 = this.editForm.get(['nhanVien'])!.value;
  soQD = '';
  ngayQD = '';
  nd = '';

  isSaving = false;

  lyDoCongTacsSharedCollection: ILyDoCongTac[] = [];
  noiDenCongTacsSharedCollection: INoiDenCongTac[] = [];
  ketQuaCongTacsSharedCollection: IKetQuaCongTac[] = [];
  kyThuatHoTrosSharedCollection: IKyThuatHoTro[] = [];
  vatTuHoTrosSharedCollection: IVatTuHoTro[] = [];
  nhanViensSharedCollection: INhanVien[] = [];



  constructor(
    protected router: Router,
    protected modalService: NgbModal,
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

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

    this.chiDaoTuyenService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe({
        next: (res: HttpResponse<IChiDaoTuyen[]>) => {
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

  trackId(_index: number, item: IChiDaoTuyen): number {
    return item.id!;
  }

  delete(): void {
    const deleteChiDaoTuyen = this.createFromForm();
    deleteChiDaoTuyen.id = this.ids;
    deleteChiDaoTuyen.soQuyetDinh = this.soQuyetDinhs;
    deleteChiDaoTuyen.ngayQuyetDinh = this.ngayQuyetDinhs;
    deleteChiDaoTuyen.soHD = this.soHDs;
    deleteChiDaoTuyen.ngayHD = this.ngayHDs;
    deleteChiDaoTuyen.noiDung = this.noiDungs;
    deleteChiDaoTuyen.ngayBatDau = this.ngayBatDaus;
    deleteChiDaoTuyen.ngayKetThuc = this.ngayKetThucs;
    deleteChiDaoTuyen.ghiChu = this.ghiChus;
    deleteChiDaoTuyen.ngayTao = this.ngayTaos;
    deleteChiDaoTuyen.soBnKhamDieuTri = this.soBnKhamDieuTris;
    deleteChiDaoTuyen.soBnPhauThuat = this.soBnPhauThuats;
    deleteChiDaoTuyen.soCanBoChuyenGiao = this.soCanBoChuyenGiaos;
    deleteChiDaoTuyen.luuTru = this.luuTrus;
    deleteChiDaoTuyen.tienAn = this.tienAns;
    deleteChiDaoTuyen.tienO = this.tienOs;
    deleteChiDaoTuyen.tienDiLai = this.tienDiLais;
    deleteChiDaoTuyen.taiLieu = this.taiLieus;
    deleteChiDaoTuyen.giangDay = this.giangDays;
    deleteChiDaoTuyen.khac = this.khacs;
    deleteChiDaoTuyen.lyDoCongTac = this.lyDoCongTac;
    deleteChiDaoTuyen.noiDenCongTac = this.noiDenCongTac;
    deleteChiDaoTuyen.ketQuaCongTac = this.ketQuaCongTac;
    deleteChiDaoTuyen.kyThuatHoTro = this.kyThuatHoTro;
    deleteChiDaoTuyen.vatTuHoTro = this.vatTuHoTro;
    deleteChiDaoTuyen.nhanVien = this.nhanVien;
    const modalRef = this.modalService.open(ChiDaoTuyenDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.chiDaoTuyen = deleteChiDaoTuyen;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }

    });
    this.ids = undefined;
    this.soQuyetDinhs = '';
    this.ngayQuyetDinhs = dayjs(this.ngayQuyetDinhs);
    this.soHDs = '';
    this.ngayHDs = dayjs(this.ngayHDs);
    this.noiDungs = '';
    this.ngayBatDaus = dayjs(this.ngayBatDaus);
    this.ngayKetThucs = dayjs(this.ngayKetThucs);
    this.ghiChus = '';
    this.ngayTaos = dayjs(this.ngayTaos);
    this.soBnKhamDieuTris = '';
    this.soBnPhauThuats = '';
    this.soCanBoChuyenGiaos = '';
    this.luuTrus = '';
    this.tienAns = '';
    this.tienOs = '';
    this.tienDiLais = '';
    this.taiLieus = '';
    this.giangDays = '';
    this.khacs = '';
    this.lyDoCongTacs = undefined;
    this.noiDenCongTacs = undefined;
    this.ketQuaCongTacs = undefined;
    this.kyThuatHoTros = undefined;
    this.vatTuHoTros = undefined;
    this.nhanViens = undefined;
  }

  create(): void{
    this.isSaving = true;
    const createChiDaoTuyen = this.createFromForm();
    this.subscribeToSaveResponse(this.chiDaoTuyenService.create(createChiDaoTuyen))
    this.ids = undefined;
    this.soQuyetDinhs = '';
    this.ngayQuyetDinhs = dayjs(this.ngayQuyetDinhs);
    this.soHDs = '';
    this.ngayHDs = dayjs(this.ngayQuyetDinhs);
    this.noiDungs = '';
    this.ngayBatDaus = dayjs(this.ngayBatDaus);
    this.ngayKetThucs = dayjs(this.ngayKetThucs);
    this.ghiChus = '';
    this.ngayTaos = dayjs(this.ngayTaos);
    this.soBnKhamDieuTris = '';
    this.soBnPhauThuats = '';
    this.soCanBoChuyenGiaos = '';
    this.luuTrus = '';
    this.tienAns = '';
    this.tienOs = '';
    this.tienDiLais = '';
    this.taiLieus = '';
    this.giangDays = '';
    this.khacs = '';
    this.lyDoCongTacs = undefined;
    this.noiDenCongTacs = undefined;
    this.ketQuaCongTacs = undefined;
    this.kyThuatHoTros = undefined;
    this.vatTuHoTros = undefined;
    this.nhanViens = undefined;
  }
  cancel(): void{
    this.ids = undefined;
    this.soQuyetDinhs = '';
    this.ngayQuyetDinhs = undefined ;
    this.soHDs = '';
    this.ngayHDs = undefined;
    this.noiDungs = '';
    this.ngayBatDaus = undefined ;
    this.ngayKetThucs = undefined ;
    this.ghiChus = '';
    this.ngayTaos = undefined;
    this.soBnKhamDieuTris = '';
    this.soBnPhauThuats = '';
    this.soCanBoChuyenGiaos = '';
    this.luuTrus = '';
    this.tienAns = '';
    this.tienOs = '';
    this.tienDiLais = '';
    this.taiLieus = '';
    this.giangDays = '';
    this.khacs = '';
    this.lyDoCongTacs = undefined;
    this.noiDenCongTacs = undefined;
    this.ketQuaCongTacs = undefined;
    this.kyThuatHoTros = undefined;
    this.vatTuHoTros = undefined;
    this.nhanViens = undefined;

  }

  previousState(): void {
    this.loadPage();
  }

  showInfor(
    id?: number,
    soQuyetDinh?: string,
    ngayQuyetDinh = dayjs(this.editForm.get(['ngayQuyetDinh'])!.value, DATE_FORMAT),
    soHD?: string,
    ngayHD = dayjs(this.editForm.get(['ngayHD'])!.value, DATE_FORMAT),
    noiDung?: string,
    ngayBatDau = dayjs(this.editForm.get(['ngayBatDau'])!.value, DATE_FORMAT),
    ngayKetThuc = dayjs(this.editForm.get(['ngayKetThuc'])!.value, DATE_FORMAT),
    ghiChu?: string,
    ngayTao = dayjs(this.editForm.get(['ngayTao'])!.value, DATE_FORMAT),
    soBnKhamDieuTri?: string,
    soBnPhauThuat?: string,
    soCanBoChuyenGiao?: string,
    luuTru?: string,
    tienAn?: string,
    tienO?: string,
    tienDiLai?: string,
    taiLieu?: string,
    giangDay?: string,
    khac?: string,
    lyDoCongTac = this.editForm.get(['lyDoCongTac'])!.value,
    noiDenCongTac = this.editForm.get(['noiDenCongTac'])!.value,
    ketQuaCongTac = this.editForm.get(['ketQuaCongTac'])!.value,
    kyThuatHoTro = this.editForm.get(['kyThuatHoTro'])!.value,
    vatTuHoTro = this.editForm.get(['vatTuHoTro'])!.value,
    nhanVien = this.editForm.get(['nhanVien'])!.value
    
  ): void {
    // eslint-disable-next-line no-console
    this.ids = id;
    this.soQuyetDinhs = soQuyetDinh;
    this.ngayQuyetDinhs = ngayQuyetDinh;
    this.soHDs = soHD;
    this.ngayHDs = ngayHD;
    this.noiDungs = noiDung;
    this.ngayBatDaus = ngayBatDau;
    this.ngayKetThucs = ngayKetThuc;
    this.ghiChus = ghiChu;
    this.ngayTaos = ngayTao;
    this.soBnKhamDieuTris = soBnKhamDieuTri;
    this.soBnPhauThuats = soBnPhauThuat;
    this.soCanBoChuyenGiaos = soCanBoChuyenGiao;
    this.luuTrus = luuTru;
    this.tienAns = tienAn;
    this.tienOs = tienO;
    this.tienDiLais = tienDiLai;
    this.taiLieus = taiLieu;
    this.giangDays = giangDay;
    this.khacs = khac;
    this.lyDoCongTacs = lyDoCongTac;
    this.noiDenCongTacs = noiDenCongTac;
    this.ketQuaCongTacs = ketQuaCongTac;
    this.kyThuatHoTros = kyThuatHoTro;
    this.vatTuHoTros = vatTuHoTro;
    this.nhanViens = nhanVien;
    
  }

  // save(): void {
  //   this.isSaving = true;
  //   const chiDaoTuyen = this.createFromForm();
  //   if (chiDaoTuyen.id !== undefined) {
  //     this.subscribeToSaveResponse(this.chiDaoTuyenService.update(chiDaoTuyen));
  //   } else {
  //     this.subscribeToSaveResponse(this.chiDaoTuyenService.create(chiDaoTuyen));
  //   }
  // }

  save(): void {
    this.isSaving = true;
    const chiDaoTuyen = this.createFromForm();
    chiDaoTuyen.id = this.id1 || this.ids;
    chiDaoTuyen.soQuyetDinh = this.soQuyetDinh1 || this.soQuyetDinhs;
    chiDaoTuyen.ngayQuyetDinh = dayjs(this.editForm.get(['ngayQuyetDinh'])!.value, DATE_FORMAT);
    chiDaoTuyen.soHD = this.soHD1 || this.soHDs;
    chiDaoTuyen.ngayHD = dayjs(this.editForm.get(['ngayHD'])!.value, DATE_FORMAT);
    chiDaoTuyen.noiDung = this.noiDung1 || this.noiDungs;
    chiDaoTuyen.ngayBatDau = dayjs(this.editForm.get(['ngayBatDau'])!.value, DATE_FORMAT);
    chiDaoTuyen.ngayKetThuc = dayjs(this.editForm.get(['ngayKetThuc'])!.value, DATE_FORMAT);
    chiDaoTuyen.ghiChu = this.ghiChu1 || this.ghiChus;
    chiDaoTuyen.ngayTao = dayjs(this.editForm.get(['ngayTao'])!.value, DATE_FORMAT);
    chiDaoTuyen.soBnKhamDieuTri = this.soBnKhamDieuTri1 || this.soBnKhamDieuTris;
    chiDaoTuyen.soBnPhauThuat = this.soBnPhauThuat1 || this.soBnPhauThuats;
    chiDaoTuyen.soCanBoChuyenGiao = this.soCanBoChuyenGiao1 || this.soCanBoChuyenGiaos;
    chiDaoTuyen.luuTru = this.luuTru1 || this.luuTrus;
    chiDaoTuyen.tienAn = this.tienAn1 || this.tienAns;
    chiDaoTuyen.tienO = this.tienO1 || this.tienOs;
    chiDaoTuyen.tienDiLai = this.tienDiLai1 || this.tienDiLais;
    chiDaoTuyen.taiLieu = this.taiLieu1 || this.taiLieus;
    chiDaoTuyen.giangDay = this.giangDay1 || this.giangDays;
    chiDaoTuyen.khac = this.khac1 || this.khacs;
    chiDaoTuyen.lyDoCongTac = this.lyDoCongTac1 || this.lyDoCongTacs;
    chiDaoTuyen.noiDenCongTac = this.noiDenCongTac1 || this.noiDenCongTacs;
    chiDaoTuyen.ketQuaCongTac = this.ketQuaCongTac1 || this.ketQuaCongTacs;
    chiDaoTuyen.kyThuatHoTro = this.kyThuatHoTro1 || this.kyThuatHoTros;
    chiDaoTuyen.vatTuHoTro = this.vatTuHoTro1 || this.vatTuHoTros;
    chiDaoTuyen.nhanVien = this.nhanVien1 || this.nhanViens;
    // eslint-disable-next-line no-console
    console.log(chiDaoTuyen);
    this.subscribeToSaveResponse(this.chiDaoTuyenService.update(chiDaoTuyen))
    this.ids = undefined;
    this.soQuyetDinhs = '';
    this.ngayQuyetDinhs = dayjs(this.ngayQuyetDinhs);
    this.soHDs = '';
    this.ngayHDs = dayjs(this.ngayHDs);
    this.noiDungs = '';
    this.ngayBatDaus = dayjs(this.ngayBatDaus);
    this.ngayKetThucs = dayjs(this.ngayKetThucs);
    this.ghiChus = '';
    this.ngayTaos = dayjs(this.ngayTaos);
    this.soBnKhamDieuTris = '';
    this.soBnPhauThuats = '';
    this.soCanBoChuyenGiaos = '';
    this.luuTrus = '';
    this.tienAns = '';
    this.tienOs = '';
    this.tienDiLais = '';
    this.taiLieus = '';
    this.giangDays = '';
    this.khacs = '';
    this.lyDoCongTacs = undefined;
    this.noiDenCongTacs = undefined;
    this.ketQuaCongTacs = undefined;
    this.kyThuatHoTros = undefined;
    this.vatTuHoTros = undefined;
    this.nhanViens = undefined;
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

  protected onSuccess(data: IChiDaoTuyen[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/chi-dao-tuyen'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? ASC : DESC),
        },
      });
    }
    this.chiDaoTuyens = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
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
