/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { AlipayFundAuthInfoRseDeleteDialogComponent } from 'app/entities/alipay-fund-auth-info-rse/alipay-fund-auth-info-rse-delete-dialog.component';
import { AlipayFundAuthInfoRseService } from 'app/entities/alipay-fund-auth-info-rse/alipay-fund-auth-info-rse.service';

describe('Component Tests', () => {
  describe('AlipayFundAuthInfoRse Management Delete Component', () => {
    let comp: AlipayFundAuthInfoRseDeleteDialogComponent;
    let fixture: ComponentFixture<AlipayFundAuthInfoRseDeleteDialogComponent>;
    let service: AlipayFundAuthInfoRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayFundAuthInfoRseDeleteDialogComponent]
      })
        .overrideTemplate(AlipayFundAuthInfoRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlipayFundAuthInfoRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlipayFundAuthInfoRseService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
