import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'alipay-fund-auth-info-rse',
        loadChildren: () =>
          import('./alipay-fund-auth-info-rse/alipay-fund-auth-info-rse.module').then(m => m.RseAlipayFundAuthInfoRseModule)
      },
      {
        path: 'alipay-freeze-request-rse',
        loadChildren: () =>
          import('./alipay-freeze-request-rse/alipay-freeze-request-rse.module').then(m => m.RseAlipayFreezeRequestRseModule)
      },
      {
        path: 'alipay-freeze-response-rse',
        loadChildren: () =>
          import('./alipay-freeze-response-rse/alipay-freeze-response-rse.module').then(m => m.RseAlipayFreezeResponseRseModule)
      },
      {
        path: 'shipping-address-rse',
        loadChildren: () => import('./shipping-address-rse/shipping-address-rse.module').then(m => m.RseShippingAddressRseModule)
      },
      {
        path: 'commodity-rse',
        loadChildren: () => import('./commodity-rse/commodity-rse.module').then(m => m.RseCommodityRseModule)
      },
      {
        path: 'category-rse',
        loadChildren: () => import('./category-rse/category-rse.module').then(m => m.RseCategoryRseModule)
      },
      {
        path: 'photo-rse',
        loadChildren: () => import('./photo-rse/photo-rse.module').then(m => m.RsePhotoRseModule)
      },
      {
        path: 'specification-rse',
        loadChildren: () => import('./specification-rse/specification-rse.module').then(m => m.RseSpecificationRseModule)
      },
      {
        path: 'user-order-rse',
        loadChildren: () => import('./user-order-rse/user-order-rse.module').then(m => m.RseUserOrderRseModule)
      },
      {
        path: 'order-item-rse',
        loadChildren: () => import('./order-item-rse/order-item-rse.module').then(m => m.RseOrderItemRseModule)
      },
      {
        path: 'item-lease-cycle-rse',
        loadChildren: () => import('./item-lease-cycle-rse/item-lease-cycle-rse.module').then(m => m.RseItemLeaseCycleRseModule)
      },
      {
        path: 'alipay-user-rse',
        loadChildren: () => import('./alipay-user-rse/alipay-user-rse.module').then(m => m.RseAlipayUserRseModule)
      },
      {
        path: 'tag-rse',
        loadChildren: () => import('./tag-rse/tag-rse.module').then(m => m.RseTagRseModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseEntityModule {}
