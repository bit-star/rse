import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  AlipayFreezeRequestRseComponent,
  AlipayFreezeRequestRseDetailComponent,
  AlipayFreezeRequestRseUpdateComponent,
  AlipayFreezeRequestRseDeletePopupComponent,
  AlipayFreezeRequestRseDeleteDialogComponent,
  alipayFreezeRequestRoute,
  alipayFreezeRequestPopupRoute
} from './';

const ENTITY_STATES = [...alipayFreezeRequestRoute, ...alipayFreezeRequestPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AlipayFreezeRequestRseComponent,
    AlipayFreezeRequestRseDetailComponent,
    AlipayFreezeRequestRseUpdateComponent,
    AlipayFreezeRequestRseDeleteDialogComponent,
    AlipayFreezeRequestRseDeletePopupComponent
  ],
  entryComponents: [
    AlipayFreezeRequestRseComponent,
    AlipayFreezeRequestRseUpdateComponent,
    AlipayFreezeRequestRseDeleteDialogComponent,
    AlipayFreezeRequestRseDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseAlipayFreezeRequestRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
