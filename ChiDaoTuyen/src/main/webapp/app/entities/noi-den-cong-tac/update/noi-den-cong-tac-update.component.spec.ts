import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { NoiDenCongTacService } from '../service/noi-den-cong-tac.service';
import { INoiDenCongTac, NoiDenCongTac } from '../noi-den-cong-tac.model';

import { NoiDenCongTacUpdateComponent } from './noi-den-cong-tac-update.component';

describe('NoiDenCongTac Management Update Component', () => {
  let comp: NoiDenCongTacUpdateComponent;
  let fixture: ComponentFixture<NoiDenCongTacUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let noiDenCongTacService: NoiDenCongTacService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [NoiDenCongTacUpdateComponent],
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
      .overrideTemplate(NoiDenCongTacUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NoiDenCongTacUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    noiDenCongTacService = TestBed.inject(NoiDenCongTacService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const noiDenCongTac: INoiDenCongTac = { id: 456 };

      activatedRoute.data = of({ noiDenCongTac });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(noiDenCongTac));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NoiDenCongTac>>();
      const noiDenCongTac = { id: 123 };
      jest.spyOn(noiDenCongTacService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ noiDenCongTac });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: noiDenCongTac }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(noiDenCongTacService.update).toHaveBeenCalledWith(noiDenCongTac);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NoiDenCongTac>>();
      const noiDenCongTac = new NoiDenCongTac();
      jest.spyOn(noiDenCongTacService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ noiDenCongTac });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: noiDenCongTac }));
      saveSubject.complete();

      // THEN
      expect(noiDenCongTacService.create).toHaveBeenCalledWith(noiDenCongTac);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NoiDenCongTac>>();
      const noiDenCongTac = { id: 123 };
      jest.spyOn(noiDenCongTacService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ noiDenCongTac });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(noiDenCongTacService.update).toHaveBeenCalledWith(noiDenCongTac);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
