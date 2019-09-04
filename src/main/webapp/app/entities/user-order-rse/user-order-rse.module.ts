import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  UserOrderRseComponent,
  UserOrderRseDetailComponent,
  UserOrderRseUpdateComponent,
  UserOrderRseDeletePopupComponent,
  UserOrderRseDeleteDialogComponent,
  userOrderRoute,
  userOrderPopupRoute
} from './';

const ENTITY_STATES = [...userOrderRoute, ...userOrderPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    UserOrderRseComponent,
    UserOrderRseDetailComponent,
    UserOrderRseUpdateComponent,
    UserOrderRseDeleteDialogComponent,
    UserOrderRseDeletePopupComponent
  ],
  entryComponents: [
    UserOrderRseComponent,
    UserOrderRseUpdateComponent,
    UserOrderRseDeleteDialogComponent,
    UserOrderRseDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseUserOrderRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
