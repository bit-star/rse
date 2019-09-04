/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { AlipayFreezeRequestRseDeleteDialogComponent } from 'app/entities/alipay-freeze-request-rse/alipay-freeze-request-rse-delete-dialog.component';
import { AlipayFreezeRequestRseService } from 'app/entities/alipay-freeze-request-rse/alipay-freeze-request-rse.service';

describe('Component Tests', () => {
  describe('AlipayFreezeRequestRse Management Delete Component', () => {
    let comp: AlipayFreezeRequestRseDeleteDialogComponent;
    let fixture: ComponentFixture<AlipayFreezeRequestRseDeleteDialogComponent>;
    let service: AlipayFreezeRequestRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayFreezeRequestRseDeleteDialogComponent]
      })
        .overrideTemplate(AlipayFreezeRequestRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlipayFreezeRequestRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlipayFreezeRequestRseService);
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
