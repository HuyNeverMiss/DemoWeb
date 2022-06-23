import dayjs from 'dayjs/esm';
import { ILyDoCongTac } from 'app/entities/ly-do-cong-tac/ly-do-cong-tac.model';
import { INoiDenCongTac } from 'app/entities/noi-den-cong-tac/noi-den-cong-tac.model';
import { IKetQuaCongTac } from 'app/entities/ket-qua-cong-tac/ket-qua-cong-tac.model';
import { IKyThuatHoTro } from 'app/entities/ky-thuat-ho-tro/ky-thuat-ho-tro.model';
import { IVatTuHoTro } from 'app/entities/vat-tu-ho-tro/vat-tu-ho-tro.model';
import { INhanVien } from 'app/entities/nhan-vien/nhan-vien.model';

export interface IChiDaoTuyen {
  id?: number;
  soQuyetDinh?: string;
  ngayQuyetDinh?: dayjs.Dayjs;
  soHD?: string;
  ngayHD?: dayjs.Dayjs;
  noiDung?: string;
  ngayBatDau?: dayjs.Dayjs;
  ngayKetThuc?: dayjs.Dayjs;
  ghiChu?: string | null;
  ngayTao?: dayjs.Dayjs;
  soBnKhamDieuTri?: string | null;
  soBnPhauThuat?: string | null;
  soCanBoChuyenGiao?: string | null;
  luuTru?: string | null;
  tienAn?: string | null;
  tienO?: string | null;
  tienDiLai?: string | null;
  taiLieu?: string | null;
  giangDay?: string | null;
  khac?: string | null;
  lyDoCongTac?: ILyDoCongTac | null;
  noiDenCongTac?: INoiDenCongTac | null;
  ketQuaCongTac?: IKetQuaCongTac | null;
  kyThuatHoTro?: IKyThuatHoTro | null;
  vatTuHoTro?: IVatTuHoTro | null;
  nhanVien?: INhanVien | null;
}

export class ChiDaoTuyen implements IChiDaoTuyen {
  constructor(
    public id?: number,
    public soQuyetDinh?: string,
    public ngayQuyetDinh?: dayjs.Dayjs,
    public soHD?: string,
    public ngayHD?: dayjs.Dayjs,
    public noiDung?: string,
    public ngayBatDau?: dayjs.Dayjs,
    public ngayKetThuc?: dayjs.Dayjs,
    public ghiChu?: string | null,
    public ngayTao?: dayjs.Dayjs,
    public soBnKhamDieuTri?: string | null,
    public soBnPhauThuat?: string | null,
    public soCanBoChuyenGiao?: string | null,
    public luuTru?: string | null,
    public tienAn?: string | null,
    public tienO?: string | null,
    public tienDiLai?: string | null,
    public taiLieu?: string | null,
    public giangDay?: string | null,
    public khac?: string | null,
    public lyDoCongTac?: ILyDoCongTac | null,
    public noiDenCongTac?: INoiDenCongTac | null,
    public ketQuaCongTac?: IKetQuaCongTac | null,
    public kyThuatHoTro?: IKyThuatHoTro | null,
    public vatTuHoTro?: IVatTuHoTro | null,
    public nhanVien?: INhanVien | null
  ) {}
}

export function getChiDaoTuyenIdentifier(chiDaoTuyen: IChiDaoTuyen): number | undefined {
  return chiDaoTuyen.id;
}
