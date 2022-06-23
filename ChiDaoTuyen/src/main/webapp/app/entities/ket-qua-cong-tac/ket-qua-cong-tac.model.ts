import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface IKetQuaCongTac {
  id?: number;
  maKetQua?: string;
  tenKetQua?: string;
  thuTuSX?: string | null;
  chiDaoTuyens?: IChiDaoTuyen[] | null;
}

export class KetQuaCongTac implements IKetQuaCongTac {
  constructor(
    public id?: number,
    public maKetQua?: string,
    public tenKetQua?: string,
    public thuTuSX?: string | null,
    public chiDaoTuyens?: IChiDaoTuyen[] | null
  ) {}
}

export function getKetQuaCongTacIdentifier(ketQuaCongTac: IKetQuaCongTac): number | undefined {
  return ketQuaCongTac.id;
}
