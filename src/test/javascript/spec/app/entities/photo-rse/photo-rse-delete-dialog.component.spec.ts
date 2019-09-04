/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { PhotoRseDeleteDialogComponent } from 'app/entities/photo-rse/photo-rse-delete-dialog.component';
import { PhotoRseService } from 'app/entities/photo-rse/photo-rse.service';

describe('Component Tests', () => {
  describe('PhotoRse Management Delete Component', () => {
    let comp: PhotoRseDeleteDialogComponent;
    let fixture: ComponentFixture<PhotoRseDeleteDialogComponent>;
    let service: PhotoRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [PhotoRseDeleteDialogComponent]
      })
        .overrideTemplate(PhotoRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PhotoRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PhotoRseService);
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
