/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { TagRseDetailComponent } from 'app/entities/tag-rse/tag-rse-detail.component';
import { TagRse } from 'app/shared/model/tag-rse.model';

describe('Component Tests', () => {
  describe('TagRse Management Detail Component', () => {
    let comp: TagRseDetailComponent;
    let fixture: ComponentFixture<TagRseDetailComponent>;
    const route = ({ data: of({ tag: new TagRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [TagRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TagRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TagRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tag).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
