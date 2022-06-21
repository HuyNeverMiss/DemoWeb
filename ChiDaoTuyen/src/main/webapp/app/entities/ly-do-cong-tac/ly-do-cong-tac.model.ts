import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface ILyDoCongTac {
  id?: number;
  maLyDo?: string | null;
  tenLyDo?: string | null;
  thuTuSX?: string | null;
  chiDaoTuyen?: IChiDaoTuyen | null;
}

export class LyDoCongTac implements ILyDoCongTac {
  constructor(
    public id?: number,
    public maLyDo?: string | null,
    public tenLyDo?: string | null,
    public thuTuSX?: string | null,
    public chiDaoTuyen?: IChiDaoTuyen | null
  ) {}
}

export function getLyDoCongTacIdentifier(lyDoCongTac: ILyDoCongTac): number | undefined {
  return lyDoCongTac.id;
}
