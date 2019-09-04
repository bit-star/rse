import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  AlipayFundAuthInfoRseComponent,
  AlipayFundAuthInfoRseDetailComponent,
  AlipayFundAuthInfoRseUpdateComponent,
  AlipayFundAuthInfoRseDeletePopupComponent,
  AlipayFundAuthInfoRseDeleteDialogComponent,
  alipayFundAuthInfoRoute,
  alipayFundAuthInfoPopupRoute
} from './';

const ENTITY_STATES = [...alipayFundAuthInfoRoute, ...alipayFundAuthInfoPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AlipayFundAuthInfoRseComponent,
    AlipayFundAuthInfoRseDetailComponent,
    AlipayFundAuthInfoRseUpdateComponent,
    AlipayFundAuthInfoRseDeleteDialogComponent,
    AlipayFundAuthInfoRseDeletePopupComponent
  ],
  entryComponents: [
    AlipayFundAuthInfoRseComponent,
    AlipayFundAuthInfoRseUpdateComponent,
    AlipayFundAuthInfoRseDeleteDialogComponent,
    AlipayFundAuthInfoRseDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseAlipayFundAuthInfoRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
