import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface IKyThuatHoTro {
  id?: number;
  maKyThuat?: string | null;
  tenKyThuat?: string | null;
  thuTuSX?: string | null;
  chiDaoTuyen?: IChiDaoTuyen | null;
}

export class KyThuatHoTro implements IKyThuatHoTro {
  constructor(
    public id?: number,
    public maKyThuat?: string | null,
    public tenKyThuat?: string | null,
    public thuTuSX?: string | null,
    public chiDaoTuyen?: IChiDaoTuyen | null
  ) {}
}

export function getKyThuatHoTroIdentifier(kyThuatHoTro: IKyThuatHoTro): number | undefined {
  return kyThuatHoTro.id;
}
