import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  AlipayFreezeResponseRseComponent,
  AlipayFreezeResponseRseDetailComponent,
  AlipayFreezeResponseRseUpdateComponent,
  AlipayFreezeResponseRseDeletePopupComponent,
  AlipayFreezeResponseRseDeleteDialogComponent,
  alipayFreezeResponseRoute,
  alipayFreezeResponsePopupRoute
} from './';

const ENTITY_STATES = [...alipayFreezeResponseRoute, ...alipayFreezeResponsePopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AlipayFreezeResponseRseComponent,
    AlipayFreezeResponseRseDetailComponent,
    AlipayFreezeResponseRseUpdateComponent,
    AlipayFreezeResponseRseDeleteDialogComponent,
    AlipayFreezeResponseRseDeletePopupComponent
  ],
  entryComponents: [
    AlipayFreezeResponseRseComponent,
    AlipayFreezeResponseRseUpdateComponent,
    AlipayFreezeResponseRseDeleteDialogComponent,
    AlipayFreezeResponseRseDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseAlipayFreezeResponseRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
