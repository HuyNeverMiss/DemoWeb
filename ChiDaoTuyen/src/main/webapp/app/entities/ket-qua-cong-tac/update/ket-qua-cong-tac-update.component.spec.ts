import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { KetQuaCongTacService } from '../service/ket-qua-cong-tac.service';
import { IKetQuaCongTac, KetQuaCongTac } from '../ket-qua-cong-tac.model';

import { KetQuaCongTacUpdateComponent } from './ket-qua-cong-tac-update.component';

describe('KetQuaCongTac Management Update Component', () => {
  let comp: KetQuaCongTacUpdateComponent;
  let fixture: ComponentFixture<KetQuaCongTacUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ketQuaCongTacService: KetQuaCongTacService;

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

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const ketQuaCongTac: IKetQuaCongTac = { id: 456 };

      activatedRoute.data = of({ ketQuaCongTac });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(ketQuaCongTac));
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
});
