import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { KyThuatHoTroService } from '../service/ky-thuat-ho-tro.service';

import { KyThuatHoTroComponent } from './ky-thuat-ho-tro.component';

describe('KyThuatHoTro Management Component', () => {
  let comp: KyThuatHoTroComponent;
  let fixture: ComponentFixture<KyThuatHoTroComponent>;
  let service: KyThuatHoTroService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [KyThuatHoTroComponent],
    })
      .overrideTemplate(KyThuatHoTroComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(KyThuatHoTroComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(KyThuatHoTroService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.kyThuatHoTros?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
