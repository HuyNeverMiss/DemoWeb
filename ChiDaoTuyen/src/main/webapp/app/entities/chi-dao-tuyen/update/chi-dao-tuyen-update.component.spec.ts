import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';
import { IChiDaoTuyen, ChiDaoTuyen } from '../chi-dao-tuyen.model';
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

import { ChiDaoTuyenUpdateComponent } from './chi-dao-tuyen-update.component';

describe('ChiDaoTuyen Management Update Component', () => {
  let comp: ChiDaoTuyenUpdateComponent;
  let fixture: ComponentFixture<ChiDaoTuyenUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let chiDaoTuyenService: ChiDaoTuyenService;
  let lyDoCongTacService: LyDoCongTacService;
  let noiDenCongTacService: NoiDenCongTacService;
  let ketQuaCongTacService: KetQuaCongTacService;
  let kyThuatHoTroService: KyThuatHoTroService;
  let vatTuHoTroService: VatTuHoTroService;
  let nhanVienService: NhanVienService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ChiDaoTuyenUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ChiDaoTuyenUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ChiDaoTuyenUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    chiDaoTuyenService = TestBed.inject(ChiDaoTuyenService);
    lyDoCongTacService = TestBed.inject(LyDoCongTacService);
    noiDenCongTacService = TestBed.inject(NoiDenCongTacService);
    ketQuaCongTacService = TestBed.inject(KetQuaCongTacService);
    kyThuatHoTroService = TestBed.inject(KyThuatHoTroService);
    vatTuHoTroService = TestBed.inject(VatTuHoTroService);
    nhanVienService = TestBed.inject(NhanVienService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call LyDoCongTac query and add missing value', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };
      const lyDoCongTac: ILyDoCongTac = { id: 68092 };
      chiDaoTuyen.lyDoCongTac = lyDoCongTac;

      const lyDoCongTacCollection: ILyDoCongTac[] = [{ id: 83853 }];
      jest.spyOn(lyDoCongTacService, 'query').mockReturnValue(of(new HttpResponse({ body: lyDoCongTacCollection })));
      const additionalLyDoCongTacs = [lyDoCongTac];
      const expectedCollection: ILyDoCongTac[] = [...additionalLyDoCongTacs, ...lyDoCongTacCollection];
      jest.spyOn(lyDoCongTacService, 'addLyDoCongTacToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(lyDoCongTacService.query).toHaveBeenCalled();
      expect(lyDoCongTacService.addLyDoCongTacToCollectionIfMissing).toHaveBeenCalledWith(lyDoCongTacCollection, ...additionalLyDoCongTacs);
      expect(comp.lyDoCongTacsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call NoiDenCongTac query and add missing value', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };
      const noiDenCongTac: INoiDenCongTac = { id: 91392 };
      chiDaoTuyen.noiDenCongTac = noiDenCongTac;

      const noiDenCongTacCollection: INoiDenCongTac[] = [{ id: 70238 }];
      jest.spyOn(noiDenCongTacService, 'query').mockReturnValue(of(new HttpResponse({ body: noiDenCongTacCollection })));
      const additionalNoiDenCongTacs = [noiDenCongTac];
      const expectedCollection: INoiDenCongTac[] = [...additionalNoiDenCongTacs, ...noiDenCongTacCollection];
      jest.spyOn(noiDenCongTacService, 'addNoiDenCongTacToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(noiDenCongTacService.query).toHaveBeenCalled();
      expect(noiDenCongTacService.addNoiDenCongTacToCollectionIfMissing).toHaveBeenCalledWith(
        noiDenCongTacCollection,
        ...additionalNoiDenCongTacs
      );
      expect(comp.noiDenCongTacsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call KetQuaCongTac query and add missing value', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };
      const ketQuaCongTac: IKetQuaCongTac = { id: 3484 };
      chiDaoTuyen.ketQuaCongTac = ketQuaCongTac;

      const ketQuaCongTacCollection: IKetQuaCongTac[] = [{ id: 8644 }];
      jest.spyOn(ketQuaCongTacService, 'query').mockReturnValue(of(new HttpResponse({ body: ketQuaCongTacCollection })));
      const additionalKetQuaCongTacs = [ketQuaCongTac];
      const expectedCollection: IKetQuaCongTac[] = [...additionalKetQuaCongTacs, ...ketQuaCongTacCollection];
      jest.spyOn(ketQuaCongTacService, 'addKetQuaCongTacToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(ketQuaCongTacService.query).toHaveBeenCalled();
      expect(ketQuaCongTacService.addKetQuaCongTacToCollectionIfMissing).toHaveBeenCalledWith(
        ketQuaCongTacCollection,
        ...additionalKetQuaCongTacs
      );
      expect(comp.ketQuaCongTacsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call KyThuatHoTro query and add missing value', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };
      const kyThuatHoTro: IKyThuatHoTro = { id: 34290 };
      chiDaoTuyen.kyThuatHoTro = kyThuatHoTro;

      const kyThuatHoTroCollection: IKyThuatHoTro[] = [{ id: 82699 }];
      jest.spyOn(kyThuatHoTroService, 'query').mockReturnValue(of(new HttpResponse({ body: kyThuatHoTroCollection })));
      const additionalKyThuatHoTros = [kyThuatHoTro];
      const expectedCollection: IKyThuatHoTro[] = [...additionalKyThuatHoTros, ...kyThuatHoTroCollection];
      jest.spyOn(kyThuatHoTroService, 'addKyThuatHoTroToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(kyThuatHoTroService.query).toHaveBeenCalled();
      expect(kyThuatHoTroService.addKyThuatHoTroToCollectionIfMissing).toHaveBeenCalledWith(
        kyThuatHoTroCollection,
        ...additionalKyThuatHoTros
      );
      expect(comp.kyThuatHoTrosSharedCollection).toEqual(expectedCollection);
    });

    it('Should call VatTuHoTro query and add missing value', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };
      const vatTuHoTro: IVatTuHoTro = { id: 65141 };
      chiDaoTuyen.vatTuHoTro = vatTuHoTro;

      const vatTuHoTroCollection: IVatTuHoTro[] = [{ id: 89507 }];
      jest.spyOn(vatTuHoTroService, 'query').mockReturnValue(of(new HttpResponse({ body: vatTuHoTroCollection })));
      const additionalVatTuHoTros = [vatTuHoTro];
      const expectedCollection: IVatTuHoTro[] = [...additionalVatTuHoTros, ...vatTuHoTroCollection];
      jest.spyOn(vatTuHoTroService, 'addVatTuHoTroToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(vatTuHoTroService.query).toHaveBeenCalled();
      expect(vatTuHoTroService.addVatTuHoTroToCollectionIfMissing).toHaveBeenCalledWith(vatTuHoTroCollection, ...additionalVatTuHoTros);
      expect(comp.vatTuHoTrosSharedCollection).toEqual(expectedCollection);
    });

    it('Should call NhanVien query and add missing value', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };
      const nhanVien: INhanVien = { id: 40536 };
      chiDaoTuyen.nhanVien = nhanVien;

      const nhanVienCollection: INhanVien[] = [{ id: 5197 }];
      jest.spyOn(nhanVienService, 'query').mockReturnValue(of(new HttpResponse({ body: nhanVienCollection })));
      const additionalNhanViens = [nhanVien];
      const expectedCollection: INhanVien[] = [...additionalNhanViens, ...nhanVienCollection];
      jest.spyOn(nhanVienService, 'addNhanVienToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(nhanVienService.query).toHaveBeenCalled();
      expect(nhanVienService.addNhanVienToCollectionIfMissing).toHaveBeenCalledWith(nhanVienCollection, ...additionalNhanViens);
      expect(comp.nhanViensSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };
      const lyDoCongTac: ILyDoCongTac = { id: 55206 };
      chiDaoTuyen.lyDoCongTac = lyDoCongTac;
      const noiDenCongTac: INoiDenCongTac = { id: 8135 };
      chiDaoTuyen.noiDenCongTac = noiDenCongTac;
      const ketQuaCongTac: IKetQuaCongTac = { id: 92422 };
      chiDaoTuyen.ketQuaCongTac = ketQuaCongTac;
      const kyThuatHoTro: IKyThuatHoTro = { id: 1031 };
      chiDaoTuyen.kyThuatHoTro = kyThuatHoTro;
      const vatTuHoTro: IVatTuHoTro = { id: 894 };
      chiDaoTuyen.vatTuHoTro = vatTuHoTro;
      const nhanVien: INhanVien = { id: 3813 };
      chiDaoTuyen.nhanVien = nhanVien;

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(chiDaoTuyen));
      expect(comp.lyDoCongTacsSharedCollection).toContain(lyDoCongTac);
      expect(comp.noiDenCongTacsSharedCollection).toContain(noiDenCongTac);
      expect(comp.ketQuaCongTacsSharedCollection).toContain(ketQuaCongTac);
      expect(comp.kyThuatHoTrosSharedCollection).toContain(kyThuatHoTro);
      expect(comp.vatTuHoTrosSharedCollection).toContain(vatTuHoTro);
      expect(comp.nhanViensSharedCollection).toContain(nhanVien);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ChiDaoTuyen>>();
      const chiDaoTuyen = { id: 123 };
      jest.spyOn(chiDaoTuyenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: chiDaoTuyen }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(chiDaoTuyenService.update).toHaveBeenCalledWith(chiDaoTuyen);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ChiDaoTuyen>>();
      const chiDaoTuyen = new ChiDaoTuyen();
      jest.spyOn(chiDaoTuyenService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: chiDaoTuyen }));
      saveSubject.complete();

      // THEN
      expect(chiDaoTuyenService.create).toHaveBeenCalledWith(chiDaoTuyen);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ChiDaoTuyen>>();
      const chiDaoTuyen = { id: 123 };
      jest.spyOn(chiDaoTuyenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(chiDaoTuyenService.update).toHaveBeenCalledWith(chiDaoTuyen);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackLyDoCongTacById', () => {
      it('Should return tracked LyDoCongTac primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackLyDoCongTacById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackNoiDenCongTacById', () => {
      it('Should return tracked NoiDenCongTac primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackNoiDenCongTacById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackKetQuaCongTacById', () => {
      it('Should return tracked KetQuaCongTac primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackKetQuaCongTacById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackKyThuatHoTroById', () => {
      it('Should return tracked KyThuatHoTro primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackKyThuatHoTroById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackVatTuHoTroById', () => {
      it('Should return tracked VatTuHoTro primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackVatTuHoTroById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackNhanVienById', () => {
      it('Should return tracked NhanVien primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackNhanVienById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
