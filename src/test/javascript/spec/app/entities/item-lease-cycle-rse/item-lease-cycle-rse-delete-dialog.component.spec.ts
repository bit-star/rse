/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { ItemLeaseCycleRseDeleteDialogComponent } from 'app/entities/item-lease-cycle-rse/item-lease-cycle-rse-delete-dialog.component';
import { ItemLeaseCycleRseService } from 'app/entities/item-lease-cycle-rse/item-lease-cycle-rse.service';

describe('Component Tests', () => {
  describe('ItemLeaseCycleRse Management Delete Component', () => {
    let comp: ItemLeaseCycleRseDeleteDialogComponent;
    let fixture: ComponentFixture<ItemLeaseCycleRseDeleteDialogComponent>;
    let service: ItemLeaseCycleRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [ItemLeaseCycleRseDeleteDialogComponent]
      })
        .overrideTemplate(ItemLeaseCycleRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ItemLeaseCycleRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ItemLeaseCycleRseService);
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
