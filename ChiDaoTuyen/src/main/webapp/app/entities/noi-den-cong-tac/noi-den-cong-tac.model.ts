import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface INoiDenCongTac {
  id?: number;
  maNoiDen?: string;
  tenNoiDen?: string;
  thuTuSX?: string | null;
  chiDaoTuyens?: IChiDaoTuyen[] | null;
}

export class NoiDenCongTac implements INoiDenCongTac {
  constructor(
    public id?: number,
    public maNoiDen?: string,
    public tenNoiDen?: string,
    public thuTuSX?: string | null,
    public chiDaoTuyens?: IChiDaoTuyen[] | null
  ) {}
}

export function getNoiDenCongTacIdentifier(noiDenCongTac: INoiDenCongTac): number | undefined {
  return noiDenCongTac.id;
}
