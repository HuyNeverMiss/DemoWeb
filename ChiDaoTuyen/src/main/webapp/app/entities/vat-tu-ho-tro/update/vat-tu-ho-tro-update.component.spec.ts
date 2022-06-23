import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { VatTuHoTroService } from '../service/vat-tu-ho-tro.service';
import { IVatTuHoTro, VatTuHoTro } from '../vat-tu-ho-tro.model';

import { VatTuHoTroUpdateComponent } from './vat-tu-ho-tro-update.component';

describe('VatTuHoTro Management Update Component', () => {
  let comp: VatTuHoTroUpdateComponent;
  let fixture: ComponentFixture<VatTuHoTroUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let vatTuHoTroService: VatTuHoTroService;

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

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const vatTuHoTro: IVatTuHoTro = { id: 456 };

      activatedRoute.data = of({ vatTuHoTro });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(vatTuHoTro));
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
});
