/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { AlipayUserRseDeleteDialogComponent } from 'app/entities/alipay-user-rse/alipay-user-rse-delete-dialog.component';
import { AlipayUserRseService } from 'app/entities/alipay-user-rse/alipay-user-rse.service';

describe('Component Tests', () => {
  describe('AlipayUserRse Management Delete Component', () => {
    let comp: AlipayUserRseDeleteDialogComponent;
    let fixture: ComponentFixture<AlipayUserRseDeleteDialogComponent>;
    let service: AlipayUserRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayUserRseDeleteDialogComponent]
      })
        .overrideTemplate(AlipayUserRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlipayUserRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlipayUserRseService);
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
