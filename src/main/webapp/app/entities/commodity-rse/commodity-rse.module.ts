import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  CommodityRseComponent,
  CommodityRseDetailComponent,
  CommodityRseUpdateComponent,
  CommodityRseDeletePopupComponent,
  CommodityRseDeleteDialogComponent,
  commodityRoute,
  commodityPopupRoute
} from './';

const ENTITY_STATES = [...commodityRoute, ...commodityPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CommodityRseComponent,
    CommodityRseDetailComponent,
    CommodityRseUpdateComponent,
    CommodityRseDeleteDialogComponent,
    CommodityRseDeletePopupComponent
  ],
  entryComponents: [
    CommodityRseComponent,
    CommodityRseUpdateComponent,
    CommodityRseDeleteDialogComponent,
    CommodityRseDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseCommodityRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
