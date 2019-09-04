/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { UserOrderRseDetailComponent } from 'app/entities/user-order-rse/user-order-rse-detail.component';
import { UserOrderRse } from 'app/shared/model/user-order-rse.model';

describe('Component Tests', () => {
  describe('UserOrderRse Management Detail Component', () => {
    let comp: UserOrderRseDetailComponent;
    let fixture: ComponentFixture<UserOrderRseDetailComponent>;
    const route = ({ data: of({ userOrder: new UserOrderRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [UserOrderRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UserOrderRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserOrderRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userOrder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
