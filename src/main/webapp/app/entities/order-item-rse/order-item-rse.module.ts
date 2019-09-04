import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  OrderItemRseComponent,
  OrderItemRseDetailComponent,
  OrderItemRseUpdateComponent,
  OrderItemRseDeletePopupComponent,
  OrderItemRseDeleteDialogComponent,
  orderItemRoute,
  orderItemPopupRoute
} from './';

const ENTITY_STATES = [...orderItemRoute, ...orderItemPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OrderItemRseComponent,
    OrderItemRseDetailComponent,
    OrderItemRseUpdateComponent,
    OrderItemRseDeleteDialogComponent,
    OrderItemRseDeletePopupComponent
  ],
  entryComponents: [
    OrderItemRseComponent,
    OrderItemRseUpdateComponent,
    OrderItemRseDeleteDialogComponent,
    OrderItemRseDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseOrderItemRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
