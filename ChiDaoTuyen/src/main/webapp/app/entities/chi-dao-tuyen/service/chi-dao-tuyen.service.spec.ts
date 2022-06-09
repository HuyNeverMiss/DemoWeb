import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IChiDaoTuyen, ChiDaoTuyen } from '../chi-dao-tuyen.model';

import { ChiDaoTuyenService } from './chi-dao-tuyen.service';

describe('ChiDaoTuyen Service', () => {
  let service: ChiDaoTuyenService;
  let httpMock: HttpTestingController;
  let elemDefault: IChiDaoTuyen;
  let expectedResult: IChiDaoTuyen | IChiDaoTuyen[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ChiDaoTuyenService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      soQuyetDinh: 'AAAAAAA',
      ngayQuyetDinh: currentDate,
      soHD: 'AAAAAAA',
      ngayHD: currentDate,
      lyDoCT: 'AAAAAAA',
      noiDung: 'AAAAAAA',
      noiCongTac: 'AAAAAAA',
      ngayBatDau: currentDate,
      ngayKetThuc: currentDate,
      ghiChu: 'AAAAAAA',
      ngayTao: currentDate,
      nhanVien: 'AAAAAAA',
      kyThuatHoTro: 'AAAAAAA',
      vatTuHoTro: 'AAAAAAA',
      soBnKhamDieuTri: 'AAAAAAA',
      soBnPhauThuat: 'AAAAAAA',
      soCanBoChuyenGiao: 'AAAAAAA',
      ketQuaCongTac: 'AAAAAAA',
      luuTru: 'AAAAAAA',
      tienAn: 'AAAAAAA',
      tienO: 'AAAAAAA',
      tienDiLai: 'AAAAAAA',
      taiLieu: 'AAAAAAA',
      giangDay: 'AAAAAAA',
      khac: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          ngayQuyetDinh: currentDate.format(DATE_TIME_FORMAT),
          ngayHD: currentDate.format(DATE_TIME_FORMAT),
          ngayBatDau: currentDate.format(DATE_TIME_FORMAT),
          ngayKetThuc: currentDate.format(DATE_TIME_FORMAT),
          ngayTao: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a ChiDaoTuyen', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          ngayQuyetDinh: currentDate.format(DATE_TIME_FORMAT),
          ngayHD: currentDate.format(DATE_TIME_FORMAT),
          ngayBatDau: currentDate.format(DATE_TIME_FORMAT),
          ngayKetThuc: currentDate.format(DATE_TIME_FORMAT),
          ngayTao: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayQuyetDinh: currentDate,
          ngayHD: currentDate,
          ngayBatDau: currentDate,
          ngayKetThuc: currentDate,
          ngayTao: currentDate,
        },
        returnedFromService
      );

      service.create(new ChiDaoTuyen()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ChiDaoTuyen', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          soQuyetDinh: 'BBBBBB',
          ngayQuyetDinh: currentDate.format(DATE_TIME_FORMAT),
          soHD: 'BBBBBB',
          ngayHD: currentDate.format(DATE_TIME_FORMAT),
          lyDoCT: 'BBBBBB',
          noiDung: 'BBBBBB',
          noiCongTac: 'BBBBBB',
          ngayBatDau: currentDate.format(DATE_TIME_FORMAT),
          ngayKetThuc: currentDate.format(DATE_TIME_FORMAT),
          ghiChu: 'BBBBBB',
          ngayTao: currentDate.format(DATE_TIME_FORMAT),
          nhanVien: 'BBBBBB',
          kyThuatHoTro: 'BBBBBB',
          vatTuHoTro: 'BBBBBB',
          soBnKhamDieuTri: 'BBBBBB',
          soBnPhauThuat: 'BBBBBB',
          soCanBoChuyenGiao: 'BBBBBB',
          ketQuaCongTac: 'BBBBBB',
          luuTru: 'BBBBBB',
          tienAn: 'BBBBBB',
          tienO: 'BBBBBB',
          tienDiLai: 'BBBBBB',
          taiLieu: 'BBBBBB',
          giangDay: 'BBBBBB',
          khac: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayQuyetDinh: currentDate,
          ngayHD: currentDate,
          ngayBatDau: currentDate,
          ngayKetThuc: currentDate,
          ngayTao: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ChiDaoTuyen', () => {
      const patchObject = Object.assign(
        {
          ngayQuyetDinh: currentDate.format(DATE_TIME_FORMAT),
          lyDoCT: 'BBBBBB',
          noiDung: 'BBBBBB',
          noiCongTac: 'BBBBBB',
          ngayKetThuc: currentDate.format(DATE_TIME_FORMAT),
          ghiChu: 'BBBBBB',
          kyThuatHoTro: 'BBBBBB',
          vatTuHoTro: 'BBBBBB',
          soBnPhauThuat: 'BBBBBB',
          ketQuaCongTac: 'BBBBBB',
          tienAn: 'BBBBBB',
          taiLieu: 'BBBBBB',
          giangDay: 'BBBBBB',
        },
        new ChiDaoTuyen()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          ngayQuyetDinh: currentDate,
          ngayHD: currentDate,
          ngayBatDau: currentDate,
          ngayKetThuc: currentDate,
          ngayTao: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ChiDaoTuyen', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          soQuyetDinh: 'BBBBBB',
          ngayQuyetDinh: currentDate.format(DATE_TIME_FORMAT),
          soHD: 'BBBBBB',
          ngayHD: currentDate.format(DATE_TIME_FORMAT),
          lyDoCT: 'BBBBBB',
          noiDung: 'BBBBBB',
          noiCongTac: 'BBBBBB',
          ngayBatDau: currentDate.format(DATE_TIME_FORMAT),
          ngayKetThuc: currentDate.format(DATE_TIME_FORMAT),
          ghiChu: 'BBBBBB',
          ngayTao: currentDate.format(DATE_TIME_FORMAT),
          nhanVien: 'BBBBBB',
          kyThuatHoTro: 'BBBBBB',
          vatTuHoTro: 'BBBBBB',
          soBnKhamDieuTri: 'BBBBBB',
          soBnPhauThuat: 'BBBBBB',
          soCanBoChuyenGiao: 'BBBBBB',
          ketQuaCongTac: 'BBBBBB',
          luuTru: 'BBBBBB',
          tienAn: 'BBBBBB',
          tienO: 'BBBBBB',
          tienDiLai: 'BBBBBB',
          taiLieu: 'BBBBBB',
          giangDay: 'BBBBBB',
          khac: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayQuyetDinh: currentDate,
          ngayHD: currentDate,
          ngayBatDau: currentDate,
          ngayKetThuc: currentDate,
          ngayTao: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a ChiDaoTuyen', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addChiDaoTuyenToCollectionIfMissing', () => {
      it('should add a ChiDaoTuyen to an empty array', () => {
        const chiDaoTuyen: IChiDaoTuyen = { id: 123 };
        expectedResult = service.addChiDaoTuyenToCollectionIfMissing([], chiDaoTuyen);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(chiDaoTuyen);
      });

      it('should not add a ChiDaoTuyen to an array that contains it', () => {
        const chiDaoTuyen: IChiDaoTuyen = { id: 123 };
        const chiDaoTuyenCollection: IChiDaoTuyen[] = [
          {
            ...chiDaoTuyen,
          },
          { id: 456 },
        ];
        expectedResult = service.addChiDaoTuyenToCollectionIfMissing(chiDaoTuyenCollection, chiDaoTuyen);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ChiDaoTuyen to an array that doesn't contain it", () => {
        const chiDaoTuyen: IChiDaoTuyen = { id: 123 };
        const chiDaoTuyenCollection: IChiDaoTuyen[] = [{ id: 456 }];
        expectedResult = service.addChiDaoTuyenToCollectionIfMissing(chiDaoTuyenCollection, chiDaoTuyen);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(chiDaoTuyen);
      });

      it('should add only unique ChiDaoTuyen to an array', () => {
        const chiDaoTuyenArray: IChiDaoTuyen[] = [{ id: 123 }, { id: 456 }, { id: 5422 }];
        const chiDaoTuyenCollection: IChiDaoTuyen[] = [{ id: 123 }];
        expectedResult = service.addChiDaoTuyenToCollectionIfMissing(chiDaoTuyenCollection, ...chiDaoTuyenArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const chiDaoTuyen: IChiDaoTuyen = { id: 123 };
        const chiDaoTuyen2: IChiDaoTuyen = { id: 456 };
        expectedResult = service.addChiDaoTuyenToCollectionIfMissing([], chiDaoTuyen, chiDaoTuyen2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(chiDaoTuyen);
        expect(expectedResult).toContain(chiDaoTuyen2);
      });

      it('should accept null and undefined values', () => {
        const chiDaoTuyen: IChiDaoTuyen = { id: 123 };
        expectedResult = service.addChiDaoTuyenToCollectionIfMissing([], null, chiDaoTuyen, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(chiDaoTuyen);
      });

      it('should return initial array if no ChiDaoTuyen is added', () => {
        const chiDaoTuyenCollection: IChiDaoTuyen[] = [{ id: 123 }];
        expectedResult = service.addChiDaoTuyenToCollectionIfMissing(chiDaoTuyenCollection, undefined, null);
        expect(expectedResult).toEqual(chiDaoTuyenCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
