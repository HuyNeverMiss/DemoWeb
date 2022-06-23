import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface INhanVien {
  id?: number;
  maNhanVien?: string;
  chucVu?: string | null;
  chiDaoTuyens?: IChiDaoTuyen[] | null;
}

export class NhanVien implements INhanVien {
  constructor(public id?: number, public maNhanVien?: string, public chucVu?: string | null, public chiDaoTuyens?: IChiDaoTuyen[] | null) {}
}

export function getNhanVienIdentifier(nhanVien: INhanVien): number | undefined {
  return nhanVien.id;
}
