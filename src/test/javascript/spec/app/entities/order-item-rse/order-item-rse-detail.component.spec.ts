/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { OrderItemRseDetailComponent } from 'app/entities/order-item-rse/order-item-rse-detail.component';
import { OrderItemRse } from 'app/shared/model/order-item-rse.model';

describe('Component Tests', () => {
  describe('OrderItemRse Management Detail Component', () => {
    let comp: OrderItemRseDetailComponent;
    let fixture: ComponentFixture<OrderItemRseDetailComponent>;
    const route = ({ data: of({ orderItem: new OrderItemRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [OrderItemRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrderItemRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderItemRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderItem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
