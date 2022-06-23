import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface ILyDoCongTac {
  id?: number;
  maLyDo?: string;
  tenLyDo?: string;
  thuTuSX?: string | null;
  chiDaoTuyens?: IChiDaoTuyen[] | null;
}

export class LyDoCongTac implements ILyDoCongTac {
  constructor(
    public id?: number,
    public maLyDo?: string,
    public tenLyDo?: string,
    public thuTuSX?: string | null,
    public chiDaoTuyens?: IChiDaoTuyen[] | null
  ) {}
}

export function getLyDoCongTacIdentifier(lyDoCongTac: ILyDoCongTac): number | undefined {
  return lyDoCongTac.id;
}
