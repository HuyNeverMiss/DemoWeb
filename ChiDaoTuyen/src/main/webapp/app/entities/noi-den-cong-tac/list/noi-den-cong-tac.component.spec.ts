import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { NoiDenCongTacService } from '../service/noi-den-cong-tac.service';

import { NoiDenCongTacComponent } from './noi-den-cong-tac.component';

describe('NoiDenCongTac Management Component', () => {
  let comp: NoiDenCongTacComponent;
  let fixture: ComponentFixture<NoiDenCongTacComponent>;
  let service: NoiDenCongTacService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [NoiDenCongTacComponent],
    })
      .overrideTemplate(NoiDenCongTacComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NoiDenCongTacComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(NoiDenCongTacService);

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
    expect(comp.noiDenCongTacs?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
