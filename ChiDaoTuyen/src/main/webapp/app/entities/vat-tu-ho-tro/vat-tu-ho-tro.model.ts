import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface IVatTuHoTro {
  id?: number;
  maVatTu?: string;
  tenVatTu?: string;
  thuTuSX?: string | null;
  chiDaoTuyens?: IChiDaoTuyen[] | null;
}

export class VatTuHoTro implements IVatTuHoTro {
  constructor(
    public id?: number,
    public maVatTu?: string,
    public tenVatTu?: string,
    public thuTuSX?: string | null,
    public chiDaoTuyens?: IChiDaoTuyen[] | null
  ) {}
}

export function getVatTuHoTroIdentifier(vatTuHoTro: IVatTuHoTro): number | undefined {
  return vatTuHoTro.id;
}
