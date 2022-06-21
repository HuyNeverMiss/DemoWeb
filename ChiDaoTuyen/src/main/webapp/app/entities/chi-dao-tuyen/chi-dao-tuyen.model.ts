import dayjs from 'dayjs/esm';

export interface IChiDaoTuyen {
  id?: number;
  soQuyetDinh?: string | null;
  ngayQuyetDinh?: dayjs.Dayjs | null;
  soHD?: string | null;
  ngayHD?: dayjs.Dayjs | null;
  lyDoCongTac?: string | null;
  noiDung?: string | null;
  noiDenCongTac?: string | null;
  ngayBatDau?: dayjs.Dayjs | null;
  ngayKetThuc?: dayjs.Dayjs | null;
  ghiChu?: string | null;
  ngayTao?: dayjs.Dayjs | null;
  nhanVien?: string | null;
  kyThuatHoTro?: string | null;
  vatTuHoTro?: string | null;
  soBnKhamDieuTri?: string | null;
  soBnPhauThuat?: string | null;
  soCanBoChuyenGiao?: string | null;
  ketQuaCongTac?: string | null;
  luuTru?: string | null;
  tienAn?: string | null;
  tienO?: string | null;
  tienDiLai?: string | null;
  taiLieu?: string | null;
  giangDay?: string | null;
  khac?: string | null;
}

export class ChiDaoTuyen implements IChiDaoTuyen {
  constructor(
    public id?: number,
    public soQuyetDinh?: string | null,
    public ngayQuyetDinh?: dayjs.Dayjs | null,
    public soHD?: string | null,
    public ngayHD?: dayjs.Dayjs | null,
    public lyDoCongTac?: string | null,
    public noiDung?: string | null,
    public noiDenCongTac?: string | null,
    public ngayBatDau?: dayjs.Dayjs | null,
    public ngayKetThuc?: dayjs.Dayjs | null,
    public ghiChu?: string | null,
    public ngayTao?: dayjs.Dayjs | null,
    public nhanVien?: string | null,
    public kyThuatHoTro?: string | null,
    public vatTuHoTro?: string | null,
    public soBnKhamDieuTri?: string | null,
    public soBnPhauThuat?: string | null,
    public soCanBoChuyenGiao?: string | null,
    public ketQuaCongTac?: string | null,
    public luuTru?: string | null,
    public tienAn?: string | null,
    public tienO?: string | null,
    public tienDiLai?: string | null,
    public taiLieu?: string | null,
    public giangDay?: string | null,
    public khac?: string | null
  ) {}
}

export function getChiDaoTuyenIdentifier(chiDaoTuyen: IChiDaoTuyen): number | undefined {
  return chiDaoTuyen.id;
}
