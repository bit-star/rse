/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { SpecificationRseDeleteDialogComponent } from 'app/entities/specification-rse/specification-rse-delete-dialog.component';
import { SpecificationRseService } from 'app/entities/specification-rse/specification-rse.service';

describe('Component Tests', () => {
  describe('SpecificationRse Management Delete Component', () => {
    let comp: SpecificationRseDeleteDialogComponent;
    let fixture: ComponentFixture<SpecificationRseDeleteDialogComponent>;
    let service: SpecificationRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [SpecificationRseDeleteDialogComponent]
      })
        .overrideTemplate(SpecificationRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecificationRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecificationRseService);
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
