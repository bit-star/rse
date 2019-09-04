import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  TagRseComponent,
  TagRseDetailComponent,
  TagRseUpdateComponent,
  TagRseDeletePopupComponent,
  TagRseDeleteDialogComponent,
  tagRoute,
  tagPopupRoute
} from './';

const ENTITY_STATES = [...tagRoute, ...tagPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [TagRseComponent, TagRseDetailComponent, TagRseUpdateComponent, TagRseDeleteDialogComponent, TagRseDeletePopupComponent],
  entryComponents: [TagRseComponent, TagRseUpdateComponent, TagRseDeleteDialogComponent, TagRseDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseTagRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
