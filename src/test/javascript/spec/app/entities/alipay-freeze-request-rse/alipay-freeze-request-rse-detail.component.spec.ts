/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { AlipayFreezeRequestRseDetailComponent } from 'app/entities/alipay-freeze-request-rse/alipay-freeze-request-rse-detail.component';
import { AlipayFreezeRequestRse } from 'app/shared/model/alipay-freeze-request-rse.model';

describe('Component Tests', () => {
  describe('AlipayFreezeRequestRse Management Detail Component', () => {
    let comp: AlipayFreezeRequestRseDetailComponent;
    let fixture: ComponentFixture<AlipayFreezeRequestRseDetailComponent>;
    const route = ({ data: of({ alipayFreezeRequest: new AlipayFreezeRequestRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayFreezeRequestRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AlipayFreezeRequestRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlipayFreezeRequestRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.alipayFreezeRequest).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
