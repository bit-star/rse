import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RseSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [RseSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [RseSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RseSharedModule {
  static forRoot() {
    return {
      ngModule: RseSharedModule
    };
  }
}
