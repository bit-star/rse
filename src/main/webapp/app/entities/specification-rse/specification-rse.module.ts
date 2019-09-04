import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  SpecificationRseComponent,
  SpecificationRseDetailComponent,
  SpecificationRseUpdateComponent,
  SpecificationRseDeletePopupComponent,
  SpecificationRseDeleteDialogComponent,
  specificationRoute,
  specificationPopupRoute
} from './';

const ENTITY_STATES = [...specificationRoute, ...specificationPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SpecificationRseComponent,
    SpecificationRseDetailComponent,
    SpecificationRseUpdateComponent,
    SpecificationRseDeleteDialogComponent,
    SpecificationRseDeletePopupComponent
  ],
  entryComponents: [
    SpecificationRseComponent,
    SpecificationRseUpdateComponent,
    SpecificationRseDeleteDialogComponent,
    SpecificationRseDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseSpecificationRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
