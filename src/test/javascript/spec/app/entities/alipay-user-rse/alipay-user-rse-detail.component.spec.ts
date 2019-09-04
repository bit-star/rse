/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { AlipayUserRseDetailComponent } from 'app/entities/alipay-user-rse/alipay-user-rse-detail.component';
import { AlipayUserRse } from 'app/shared/model/alipay-user-rse.model';

describe('Component Tests', () => {
  describe('AlipayUserRse Management Detail Component', () => {
    let comp: AlipayUserRseDetailComponent;
    let fixture: ComponentFixture<AlipayUserRseDetailComponent>;
    const route = ({ data: of({ alipayUser: new AlipayUserRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayUserRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AlipayUserRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlipayUserRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.alipayUser).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
