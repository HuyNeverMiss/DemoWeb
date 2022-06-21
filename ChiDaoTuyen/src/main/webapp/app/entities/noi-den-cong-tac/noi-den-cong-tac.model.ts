import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface INoiDenCongTac {
  id?: number;
  maNoiDen?: string | null;
  tenNoiDen?: string | null;
  thuTuSX?: string | null;
  chiDaoTuyen?: IChiDaoTuyen | null;
}

export class NoiDenCongTac implements INoiDenCongTac {
  constructor(
    public id?: number,
    public maNoiDen?: string | null,
    public tenNoiDen?: string | null,
    public thuTuSX?: string | null,
    public chiDaoTuyen?: IChiDaoTuyen | null
  ) {}
}

export function getNoiDenCongTacIdentifier(noiDenCongTac: INoiDenCongTac): number | undefined {
  return noiDenCongTac.id;
}
