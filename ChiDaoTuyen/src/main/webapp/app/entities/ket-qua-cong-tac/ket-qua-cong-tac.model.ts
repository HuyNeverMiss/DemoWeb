import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface IKetQuaCongTac {
  id?: number;
  maKetQua?: string | null;
  tenKetQua?: string | null;
  thuTuSX?: string | null;
  chiDaoTuyen?: IChiDaoTuyen | null;
}

export class KetQuaCongTac implements IKetQuaCongTac {
  constructor(
    public id?: number,
    public maKetQua?: string | null,
    public tenKetQua?: string | null,
    public thuTuSX?: string | null,
    public chiDaoTuyen?: IChiDaoTuyen | null
  ) {}
}

export function getKetQuaCongTacIdentifier(ketQuaCongTac: IKetQuaCongTac): number | undefined {
  return ketQuaCongTac.id;
}
