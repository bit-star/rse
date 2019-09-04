/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { ShippingAddressRseDetailComponent } from 'app/entities/shipping-address-rse/shipping-address-rse-detail.component';
import { ShippingAddressRse } from 'app/shared/model/shipping-address-rse.model';

describe('Component Tests', () => {
  describe('ShippingAddressRse Management Detail Component', () => {
    let comp: ShippingAddressRseDetailComponent;
    let fixture: ComponentFixture<ShippingAddressRseDetailComponent>;
    const route = ({ data: of({ shippingAddress: new ShippingAddressRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [ShippingAddressRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ShippingAddressRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShippingAddressRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.shippingAddress).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
