/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { PhotoRseUpdateComponent } from 'app/entities/photo-rse/photo-rse-update.component';
import { PhotoRseService } from 'app/entities/photo-rse/photo-rse.service';
import { PhotoRse } from 'app/shared/model/photo-rse.model';

describe('Component Tests', () => {
  describe('PhotoRse Management Update Component', () => {
    let comp: PhotoRseUpdateComponent;
    let fixture: ComponentFixture<PhotoRseUpdateComponent>;
    let service: PhotoRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [PhotoRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PhotoRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PhotoRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PhotoRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PhotoRse(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PhotoRse();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
