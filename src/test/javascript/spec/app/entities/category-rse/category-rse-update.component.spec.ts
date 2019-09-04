/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { CategoryRseUpdateComponent } from 'app/entities/category-rse/category-rse-update.component';
import { CategoryRseService } from 'app/entities/category-rse/category-rse.service';
import { CategoryRse } from 'app/shared/model/category-rse.model';

describe('Component Tests', () => {
  describe('CategoryRse Management Update Component', () => {
    let comp: CategoryRseUpdateComponent;
    let fixture: ComponentFixture<CategoryRseUpdateComponent>;
    let service: CategoryRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [CategoryRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CategoryRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategoryRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoryRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategoryRse(123);
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
        const entity = new CategoryRse();
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
