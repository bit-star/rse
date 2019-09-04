import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  AlipayUserRseComponent,
  AlipayUserRseDetailComponent,
  AlipayUserRseUpdateComponent,
  AlipayUserRseDeletePopupComponent,
  AlipayUserRseDeleteDialogComponent,
  alipayUserRoute,
  alipayUserPopupRoute
} from './';

const ENTITY_STATES = [...alipayUserRoute, ...alipayUserPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AlipayUserRseComponent,
    AlipayUserRseDetailComponent,
    AlipayUserRseUpdateComponent,
    AlipayUserRseDeleteDialogComponent,
    AlipayUserRseDeletePopupComponent
  ],
  entryComponents: [
    AlipayUserRseComponent,
    AlipayUserRseUpdateComponent,
    AlipayUserRseDeleteDialogComponent,
    AlipayUserRseDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseAlipayUserRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
