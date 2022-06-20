import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';

import { ChiDaoTuyenComponent } from './chi-dao-tuyen.component';

describe('ChiDaoTuyen Management Component', () => {
  let comp: ChiDaoTuyenComponent;
  let fixture: ComponentFixture<ChiDaoTuyenComponent>;
  let service: ChiDaoTuyenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ChiDaoTuyenComponent],
    })
      .overrideTemplate(ChiDaoTuyenComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ChiDaoTuyenComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ChiDaoTuyenService);

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
    expect(comp.chiDaoTuyens?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
