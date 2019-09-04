import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  ItemLeaseCycleRseComponent,
  ItemLeaseCycleRseDetailComponent,
  ItemLeaseCycleRseUpdateComponent,
  ItemLeaseCycleRseDeletePopupComponent,
  ItemLeaseCycleRseDeleteDialogComponent,
  itemLeaseCycleRoute,
  itemLeaseCyclePopupRoute
} from './';

const ENTITY_STATES = [...itemLeaseCycleRoute, ...itemLeaseCyclePopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ItemLeaseCycleRseComponent,
    ItemLeaseCycleRseDetailComponent,
    ItemLeaseCycleRseUpdateComponent,
    ItemLeaseCycleRseDeleteDialogComponent,
    ItemLeaseCycleRseDeletePopupComponent
  ],
  entryComponents: [
    ItemLeaseCycleRseComponent,
    ItemLeaseCycleRseUpdateComponent,
    ItemLeaseCycleRseDeleteDialogComponent,
    ItemLeaseCycleRseDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseItemLeaseCycleRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
