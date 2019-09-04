/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { AlipayFreezeResponseRseDeleteDialogComponent } from 'app/entities/alipay-freeze-response-rse/alipay-freeze-response-rse-delete-dialog.component';
import { AlipayFreezeResponseRseService } from 'app/entities/alipay-freeze-response-rse/alipay-freeze-response-rse.service';

describe('Component Tests', () => {
  describe('AlipayFreezeResponseRse Management Delete Component', () => {
    let comp: AlipayFreezeResponseRseDeleteDialogComponent;
    let fixture: ComponentFixture<AlipayFreezeResponseRseDeleteDialogComponent>;
    let service: AlipayFreezeResponseRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayFreezeResponseRseDeleteDialogComponent]
      })
        .overrideTemplate(AlipayFreezeResponseRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlipayFreezeResponseRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlipayFreezeResponseRseService);
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
