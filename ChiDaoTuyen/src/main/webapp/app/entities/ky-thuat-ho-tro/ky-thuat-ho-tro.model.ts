import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface IKyThuatHoTro {
  id?: number;
  maKyThuat?: string;
  tenKyThuat?: string;
  thuTuSX?: string | null;
  chiDaoTuyens?: IChiDaoTuyen[] | null;
}

export class KyThuatHoTro implements IKyThuatHoTro {
  constructor(
    public id?: number,
    public maKyThuat?: string,
    public tenKyThuat?: string,
    public thuTuSX?: string | null,
    public chiDaoTuyens?: IChiDaoTuyen[] | null
  ) {}
}

export function getKyThuatHoTroIdentifier(kyThuatHoTro: IKyThuatHoTro): number | undefined {
  return kyThuatHoTro.id;
}
