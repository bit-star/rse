/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { TagRseUpdateComponent } from 'app/entities/tag-rse/tag-rse-update.component';
import { TagRseService } from 'app/entities/tag-rse/tag-rse.service';
import { TagRse } from 'app/shared/model/tag-rse.model';

describe('Component Tests', () => {
  describe('TagRse Management Update Component', () => {
    let comp: TagRseUpdateComponent;
    let fixture: ComponentFixture<TagRseUpdateComponent>;
    let service: TagRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [TagRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TagRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TagRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TagRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TagRse(123);
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
        const entity = new TagRse();
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
