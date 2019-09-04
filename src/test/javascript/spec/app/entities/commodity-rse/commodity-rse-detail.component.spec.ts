/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { CommodityRseDetailComponent } from 'app/entities/commodity-rse/commodity-rse-detail.component';
import { CommodityRse } from 'app/shared/model/commodity-rse.model';

describe('Component Tests', () => {
  describe('CommodityRse Management Detail Component', () => {
    let comp: CommodityRseDetailComponent;
    let fixture: ComponentFixture<CommodityRseDetailComponent>;
    const route = ({ data: of({ commodity: new CommodityRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [CommodityRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CommodityRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommodityRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.commodity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
