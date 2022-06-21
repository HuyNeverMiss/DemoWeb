import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';
import { IChiDaoTuyen, ChiDaoTuyen } from '../chi-dao-tuyen.model';

import { ChiDaoTuyenUpdateComponent } from './chi-dao-tuyen-update.component';

describe('ChiDaoTuyen Management Update Component', () => {
  let comp: ChiDaoTuyenUpdateComponent;
  let fixture: ComponentFixture<ChiDaoTuyenUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let chiDaoTuyenService: ChiDaoTuyenService;

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

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(chiDaoTuyen));
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
});
