/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RseTestModule } from '../../../test.module';
import { CategoryRseDeleteDialogComponent } from 'app/entities/category-rse/category-rse-delete-dialog.component';
import { CategoryRseService } from 'app/entities/category-rse/category-rse.service';

describe('Component Tests', () => {
  describe('CategoryRse Management Delete Component', () => {
    let comp: CategoryRseDeleteDialogComponent;
    let fixture: ComponentFixture<CategoryRseDeleteDialogComponent>;
    let service: CategoryRseService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [CategoryRseDeleteDialogComponent]
      })
        .overrideTemplate(CategoryRseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoryRseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoryRseService);
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
