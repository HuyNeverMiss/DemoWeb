import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'ly-do-cong-tac',
        data: { pageTitle: 'chiDaoTuyenApp.lyDoCongTac.home.title' },
        loadChildren: () => import('./ly-do-cong-tac/ly-do-cong-tac.module').then(m => m.LyDoCongTacModule),
      },
      {
        path: 'ket-qua-cong-tac',
        data: { pageTitle: 'chiDaoTuyenApp.ketQuaCongTac.home.title' },
        loadChildren: () => import('./ket-qua-cong-tac/ket-qua-cong-tac.module').then(m => m.KetQuaCongTacModule),
      },
      {
        path: 'ky-thuat-ho-tro',
        data: { pageTitle: 'chiDaoTuyenApp.kyThuatHoTro.home.title' },
        loadChildren: () => import('./ky-thuat-ho-tro/ky-thuat-ho-tro.module').then(m => m.KyThuatHoTroModule),
      },
      {
        path: 'noi-den-cong-tac',
        data: { pageTitle: 'chiDaoTuyenApp.noiDenCongTac.home.title' },
        loadChildren: () => import('./noi-den-cong-tac/noi-den-cong-tac.module').then(m => m.NoiDenCongTacModule),
      },
      {
        path: 'vat-tu-ho-tro',
        data: { pageTitle: 'chiDaoTuyenApp.vatTuHoTro.home.title' },
        loadChildren: () => import('./vat-tu-ho-tro/vat-tu-ho-tro.module').then(m => m.VatTuHoTroModule),
      },
      {
        path: 'chi-dao-tuyen',
        data: { pageTitle: 'chiDaoTuyenApp.chiDaoTuyen.home.title' },
        loadChildren: () => import('./chi-dao-tuyen/chi-dao-tuyen.module').then(m => m.ChiDaoTuyenModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
