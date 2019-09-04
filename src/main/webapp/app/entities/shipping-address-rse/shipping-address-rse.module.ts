import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  ShippingAddressRseComponent,
  ShippingAddressRseDetailComponent,
  ShippingAddressRseUpdateComponent,
  ShippingAddressRseDeletePopupComponent,
  ShippingAddressRseDeleteDialogComponent,
  shippingAddressRoute,
  shippingAddressPopupRoute
} from './';

const ENTITY_STATES = [...shippingAddressRoute, ...shippingAddressPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ShippingAddressRseComponent,
    ShippingAddressRseDetailComponent,
    ShippingAddressRseUpdateComponent,
    ShippingAddressRseDeleteDialogComponent,
    ShippingAddressRseDeletePopupComponent
  ],
  entryComponents: [
    ShippingAddressRseComponent,
    ShippingAddressRseUpdateComponent,
    ShippingAddressRseDeleteDialogComponent,
    ShippingAddressRseDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseShippingAddressRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
