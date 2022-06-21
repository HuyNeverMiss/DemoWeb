import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { KetQuaCongTacService } from '../service/ket-qua-cong-tac.service';
import { IKetQuaCongTac, KetQuaCongTac } from '../ket-qua-cong-tac.model';
import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';
import { ChiDaoTuyenService } from 'app/entities/chi-dao-tuyen/service/chi-dao-tuyen.service';

import { KetQuaCongTacUpdateComponent } from './ket-qua-cong-tac-update.component';

describe('KetQuaCongTac Management Update Component', () => {
  let comp: KetQuaCongTacUpdateComponent;
  let fixture: ComponentFixture<KetQuaCongTacUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ketQuaCongTacService: KetQuaCongTacService;
  let chiDaoTuyenService: ChiDaoTuyenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [KetQuaCongTacUpdateComponent],
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
      .overrideTemplate(KetQuaCongTacUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(KetQuaCongTacUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ketQuaCongTacService = TestBed.inject(KetQuaCongTacService);
    chiDaoTuyenService = TestBed.inject(ChiDaoTuyenService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call chiDaoTuyen query and add missing value', () => {
      const ketQuaCongTac: IKetQuaCongTac = { id: 456 };
      const chiDaoTuyen: IChiDaoTuyen = { id: 2586 };
      ketQuaCongTac.chiDaoTuyen = chiDaoTuyen;

      const chiDaoTuyenCollection: IChiDaoTuyen[] = [{ id: 25983 }];
      jest.spyOn(chiDaoTuyenService, 'query').mockReturnValue(of(new HttpResponse({ body: chiDaoTuyenCollection })));
      const expectedCollection: IChiDaoTuyen[] = [chiDaoTuyen, ...chiDaoTuyenCollection];
      jest.spyOn(chiDaoTuyenService, 'addChiDaoTuyenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ ketQuaCongTac });
      comp.ngOnInit();

      expect(chiDaoTuyenService.query).toHaveBeenCalled();
      expect(chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing).toHaveBeenCalledWith(chiDaoTuyenCollection, chiDaoTuyen);
      expect(comp.chiDaoTuyensCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const ketQuaCongTac: IKetQuaCongTac = { id: 456 };
      const chiDaoTuyen: IChiDaoTuyen = { id: 78045 };
      ketQuaCongTac.chiDaoTuyen = chiDaoTuyen;

      activatedRoute.data = of({ ketQuaCongTac });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(ketQuaCongTac));
      expect(comp.chiDaoTuyensCollection).toContain(chiDaoTuyen);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<KetQuaCongTac>>();
      const ketQuaCongTac = { id: 123 };
      jest.spyOn(ketQuaCongTacService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ketQuaCongTac });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ketQuaCongTac }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(ketQuaCongTacService.update).toHaveBeenCalledWith(ketQuaCongTac);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<KetQuaCongTac>>();
      const ketQuaCongTac = new KetQuaCongTac();
      jest.spyOn(ketQuaCongTacService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ketQuaCongTac });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ketQuaCongTac }));
      saveSubject.complete();

      // THEN
      expect(ketQuaCongTacService.create).toHaveBeenCalledWith(ketQuaCongTac);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<KetQuaCongTac>>();
      const ketQuaCongTac = { id: 123 };
      jest.spyOn(ketQuaCongTacService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ketQuaCongTac });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ketQuaCongTacService.update).toHaveBeenCalledWith(ketQuaCongTac);
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
