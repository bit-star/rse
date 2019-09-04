/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { OrderItemRseDeleteDialogComponent } from 'app/entities/order-item-rse/order-item-rse-delete-dialog.component';
import { OrderItemRseService } from 'app/entities/order-item-rse/order-item-rse.service';

describe('Component Tests', () => {
  describe('OrderItemRse Management Delete Component', () => {
    let comp: OrderItemRseDeleteDialogComponent;
    let fixture: ComponentFixture<OrderItemRseDeleteDialogComponent>;
    let service: OrderItemRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [OrderItemRseDeleteDialogComponent]
      })
        .overrideTemplate(OrderItemRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderItemRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderItemRseService);
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
