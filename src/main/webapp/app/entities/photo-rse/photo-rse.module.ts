import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { RseSharedModule } from 'app/shared';
import {
  PhotoRseComponent,
  PhotoRseDetailComponent,
  PhotoRseUpdateComponent,
  PhotoRseDeletePopupComponent,
  PhotoRseDeleteDialogComponent,
  photoRoute,
  photoPopupRoute
} from './';

const ENTITY_STATES = [...photoRoute, ...photoPopupRoute];

@NgModule({
  imports: [RseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PhotoRseComponent,
    PhotoRseDetailComponent,
    PhotoRseUpdateComponent,
    PhotoRseDeleteDialogComponent,
    PhotoRseDeletePopupComponent
  ],
  entryComponents: [PhotoRseComponent, PhotoRseUpdateComponent, PhotoRseDeleteDialogComponent, PhotoRseDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RsePhotoRseModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
