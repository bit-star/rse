/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { ShippingAddressRseDeleteDialogComponent } from 'app/entities/shipping-address-rse/shipping-address-rse-delete-dialog.component';
import { ShippingAddressRseService } from 'app/entities/shipping-address-rse/shipping-address-rse.service';

describe('Component Tests', () => {
  describe('ShippingAddressRse Management Delete Component', () => {
    let comp: ShippingAddressRseDeleteDialogComponent;
    let fixture: ComponentFixture<ShippingAddressRseDeleteDialogComponent>;
    let service: ShippingAddressRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [ShippingAddressRseDeleteDialogComponent]
      })
        .overrideTemplate(ShippingAddressRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShippingAddressRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShippingAddressRseService);
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
