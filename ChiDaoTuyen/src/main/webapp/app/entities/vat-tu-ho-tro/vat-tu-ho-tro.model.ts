import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface IVatTuHoTro {
  id?: number;
  maVatTu?: string | null;
  tenVatTu?: string | null;
  thuTuSX?: string | null;
  chiDaoTuyen?: IChiDaoTuyen | null;
}

export class VatTuHoTro implements IVatTuHoTro {
  constructor(
    public id?: number,
    public maVatTu?: string | null,
    public tenVatTu?: string | null,
    public thuTuSX?: string | null,
    public chiDaoTuyen?: IChiDaoTuyen | null
  ) {}
}

export function getVatTuHoTroIdentifier(vatTuHoTro: IVatTuHoTro): number | undefined {
  return vatTuHoTro.id;
}
