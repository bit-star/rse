/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { ItemLeaseCycleRseDetailComponent } from 'app/entities/item-lease-cycle-rse/item-lease-cycle-rse-detail.component';
import { ItemLeaseCycleRse } from 'app/shared/model/item-lease-cycle-rse.model';

describe('Component Tests', () => {
  describe('ItemLeaseCycleRse Management Detail Component', () => {
    let comp: ItemLeaseCycleRseDetailComponent;
    let fixture: ComponentFixture<ItemLeaseCycleRseDetailComponent>;
    const route = ({ data: of({ itemLeaseCycle: new ItemLeaseCycleRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [ItemLeaseCycleRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ItemLeaseCycleRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ItemLeaseCycleRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.itemLeaseCycle).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
