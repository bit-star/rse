/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { ItemLeaseCycleRseUpdateComponent } from 'app/entities/item-lease-cycle-rse/item-lease-cycle-rse-update.component';
import { ItemLeaseCycleRseService } from 'app/entities/item-lease-cycle-rse/item-lease-cycle-rse.service';
import { ItemLeaseCycleRse } from 'app/shared/model/item-lease-cycle-rse.model';

describe('Component Tests', () => {
  describe('ItemLeaseCycleRse Management Update Component', () => {
    let comp: ItemLeaseCycleRseUpdateComponent;
    let fixture: ComponentFixture<ItemLeaseCycleRseUpdateComponent>;
    let service: ItemLeaseCycleRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [ItemLeaseCycleRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ItemLeaseCycleRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ItemLeaseCycleRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ItemLeaseCycleRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ItemLeaseCycleRse(123);
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
        const entity = new ItemLeaseCycleRse();
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
