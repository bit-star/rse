/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { CategoryRseDetailComponent } from 'app/entities/category-rse/category-rse-detail.component';
import { CategoryRse } from 'app/shared/model/category-rse.model';

describe('Component Tests', () => {
  describe('CategoryRse Management Detail Component', () => {
    let comp: CategoryRseDetailComponent;
    let fixture: ComponentFixture<CategoryRseDetailComponent>;
    const route = ({ data: of({ category: new CategoryRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [CategoryRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CategoryRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoryRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.category).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
