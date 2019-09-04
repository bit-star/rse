/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { SpecificationRseUpdateComponent } from 'app/entities/specification-rse/specification-rse-update.component';
import { SpecificationRseService } from 'app/entities/specification-rse/specification-rse.service';
import { SpecificationRse } from 'app/shared/model/specification-rse.model';

describe('Component Tests', () => {
  describe('SpecificationRse Management Update Component', () => {
    let comp: SpecificationRseUpdateComponent;
    let fixture: ComponentFixture<SpecificationRseUpdateComponent>;
    let service: SpecificationRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [SpecificationRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SpecificationRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SpecificationRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SpecificationRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SpecificationRse(123);
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
        const entity = new SpecificationRse();
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
