import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  CategoryRseComponent,
  CategoryRseDetailComponent,
  CategoryRseUpdateComponent,
  CategoryRseDeletePopupComponent,
  CategoryRseDeleteDialogComponent,
  categoryRoute,
  categoryPopupRoute
} from './';

const ENTITY_STATES = [...categoryRoute, ...categoryPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CategoryRseComponent,
    CategoryRseDetailComponent,
    CategoryRseUpdateComponent,
    CategoryRseDeleteDialogComponent,
    CategoryRseDeletePopupComponent
  ],
  entryComponents: [CategoryRseComponent, CategoryRseUpdateComponent, CategoryRseDeleteDialogComponent, CategoryRseDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseCategoryRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
