/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { AlipayFreezeResponseRseDetailComponent } from 'app/entities/alipay-freeze-response-rse/alipay-freeze-response-rse-detail.component';
import { AlipayFreezeResponseRse } from 'app/shared/model/alipay-freeze-response-rse.model';

describe('Component Tests', () => {
  describe('AlipayFreezeResponseRse Management Detail Component', () => {
    let comp: AlipayFreezeResponseRseDetailComponent;
    let fixture: ComponentFixture<AlipayFreezeResponseRseDetailComponent>;
    const route = ({ data: of({ alipayFreezeResponse: new AlipayFreezeResponseRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayFreezeResponseRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AlipayFreezeResponseRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlipayFreezeResponseRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.alipayFreezeResponse).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
