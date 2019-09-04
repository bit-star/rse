/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { AlipayFundAuthInfoRseDetailComponent } from 'app/entities/alipay-fund-auth-info-rse/alipay-fund-auth-info-rse-detail.component';
import { AlipayFundAuthInfoRse } from 'app/shared/model/alipay-fund-auth-info-rse.model';

describe('Component Tests', () => {
  describe('AlipayFundAuthInfoRse Management Detail Component', () => {
    let comp: AlipayFundAuthInfoRseDetailComponent;
    let fixture: ComponentFixture<AlipayFundAuthInfoRseDetailComponent>;
    const route = ({ data: of({ alipayFundAuthInfo: new AlipayFundAuthInfoRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayFundAuthInfoRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AlipayFundAuthInfoRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlipayFundAuthInfoRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.alipayFundAuthInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
