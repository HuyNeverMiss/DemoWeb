import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { VatTuHoTroService } from '../service/vat-tu-ho-tro.service';
import { IVatTuHoTro, VatTuHoTro } from '../vat-tu-ho-tro.model';
import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';
import { ChiDaoTuyenService } from 'app/entities/chi-dao-tuyen/service/chi-dao-tuyen.service';

import { VatTuHoTroUpdateComponent } from './vat-tu-ho-tro-update.component';

describe('VatTuHoTro Management Update Component', () => {
  let comp: VatTuHoTroUpdateComponent;
  let fixture: ComponentFixture<VatTuHoTroUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let vatTuHoTroService: VatTuHoTroService;
  let chiDaoTuyenService: ChiDaoTuyenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [VatTuHoTroUpdateComponent],
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
      .overrideTemplate(VatTuHoTroUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VatTuHoTroUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    vatTuHoTroService = TestBed.inject(VatTuHoTroService);
    chiDaoTuyenService = TestBed.inject(ChiDaoTuyenService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call chiDaoTuyen query and add missing value', () => {
      const vatTuHoTro: IVatTuHoTro = { id: 456 };
      const chiDaoTuyen: IChiDaoTuyen = { id: 28678 };
      vatTuHoTro.chiDaoTuyen = chiDaoTuyen;

      const chiDaoTuyenCollection: IChiDaoTuyen[] = [{ id: 93819 }];
      jest.spyOn(chiDaoTuyenService, 'query').mockReturnValue(of(new HttpResponse({ body: chiDaoTuyenCollection })));
      const expectedCollection: IChiDaoTuyen[] = [chiDaoTuyen, ...chiDaoTuyenCollection];
      jest.spyOn(chiDaoTuyenService, 'addChiDaoTuyenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ vatTuHoTro });
      comp.ngOnInit();

      expect(chiDaoTuyenService.query).toHaveBeenCalled();
      expect(chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing).toHaveBeenCalledWith(chiDaoTuyenCollection, chiDaoTuyen);
      expect(comp.chiDaoTuyensCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const vatTuHoTro: IVatTuHoTro = { id: 456 };
      const chiDaoTuyen: IChiDaoTuyen = { id: 84044 };
      vatTuHoTro.chiDaoTuyen = chiDaoTuyen;

      activatedRoute.data = of({ vatTuHoTro });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(vatTuHoTro));
      expect(comp.chiDaoTuyensCollection).toContain(chiDaoTuyen);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<VatTuHoTro>>();
      const vatTuHoTro = { id: 123 };
      jest.spyOn(vatTuHoTroService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vatTuHoTro });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vatTuHoTro }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(vatTuHoTroService.update).toHaveBeenCalledWith(vatTuHoTro);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<VatTuHoTro>>();
      const vatTuHoTro = new VatTuHoTro();
      jest.spyOn(vatTuHoTroService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vatTuHoTro });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: vatTuHoTro }));
      saveSubject.complete();

      // THEN
      expect(vatTuHoTroService.create).toHaveBeenCalledWith(vatTuHoTro);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<VatTuHoTro>>();
      const vatTuHoTro = { id: 123 };
      jest.spyOn(vatTuHoTroService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ vatTuHoTro });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(vatTuHoTroService.update).toHaveBeenCalledWith(vatTuHoTro);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackChiDaoTuyenById', () => {
      it('Should return tracked ChiDaoTuyen primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackChiDaoTuyenById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
