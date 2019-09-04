/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { UserOrderRseDeleteDialogComponent } from 'app/entities/user-order-rse/user-order-rse-delete-dialog.component';
import { UserOrderRseService } from 'app/entities/user-order-rse/user-order-rse.service';

describe('Component Tests', () => {
  describe('UserOrderRse Management Delete Component', () => {
    let comp: UserOrderRseDeleteDialogComponent;
    let fixture: ComponentFixture<UserOrderRseDeleteDialogComponent>;
    let service: UserOrderRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [UserOrderRseDeleteDialogComponent]
      })
        .overrideTemplate(UserOrderRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserOrderRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserOrderRseService);
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
