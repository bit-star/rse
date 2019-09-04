/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { ShippingAddressRseUpdateComponent } from 'app/entities/shipping-address-rse/shipping-address-rse-update.component';
import { ShippingAddressRseService } from 'app/entities/shipping-address-rse/shipping-address-rse.service';
import { ShippingAddressRse } from 'app/shared/model/shipping-address-rse.model';

describe('Component Tests', () => {
  describe('ShippingAddressRse Management Update Component', () => {
    let comp: ShippingAddressRseUpdateComponent;
    let fixture: ComponentFixture<ShippingAddressRseUpdateComponent>;
    let service: ShippingAddressRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [ShippingAddressRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ShippingAddressRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShippingAddressRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShippingAddressRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ShippingAddressRse(123);
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
        const entity = new ShippingAddressRse();
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
