/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { SpecificationRseDetailComponent } from 'app/entities/specification-rse/specification-rse-detail.component';
import { SpecificationRse } from 'app/shared/model/specification-rse.model';

describe('Component Tests', () => {
  describe('SpecificationRse Management Detail Component', () => {
    let comp: SpecificationRseDetailComponent;
    let fixture: ComponentFixture<SpecificationRseDetailComponent>;
    const route = ({ data: of({ specification: new SpecificationRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [SpecificationRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SpecificationRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SpecificationRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.specification).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
