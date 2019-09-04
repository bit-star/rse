/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { OrderItemRseUpdateComponent } from 'app/entities/order-item-rse/order-item-rse-update.component';
import { OrderItemRseService } from 'app/entities/order-item-rse/order-item-rse.service';
import { OrderItemRse } from 'app/shared/model/order-item-rse.model';

describe('Component Tests', () => {
  describe('OrderItemRse Management Update Component', () => {
    let comp: OrderItemRseUpdateComponent;
    let fixture: ComponentFixture<OrderItemRseUpdateComponent>;
    let service: OrderItemRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [OrderItemRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderItemRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderItemRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderItemRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderItemRse(123);
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
        const entity = new OrderItemRse();
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
