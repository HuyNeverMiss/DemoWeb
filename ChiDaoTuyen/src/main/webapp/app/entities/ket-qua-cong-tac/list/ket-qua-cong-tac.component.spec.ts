import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { KetQuaCongTacService } from '../service/ket-qua-cong-tac.service';

import { KetQuaCongTacComponent } from './ket-qua-cong-tac.component';

describe('KetQuaCongTac Management Component', () => {
  let comp: KetQuaCongTacComponent;
  let fixture: ComponentFixture<KetQuaCongTacComponent>;
  let service: KetQuaCongTacService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [KetQuaCongTacComponent],
    })
      .overrideTemplate(KetQuaCongTacComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(KetQuaCongTacComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(KetQuaCongTacService);

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
    expect(comp.ketQuaCongTacs?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
