/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { PhotoRseDetailComponent } from 'app/entities/photo-rse/photo-rse-detail.component';
import { PhotoRse } from 'app/shared/model/photo-rse.model';

describe('Component Tests', () => {
  describe('PhotoRse Management Detail Component', () => {
    let comp: PhotoRseDetailComponent;
    let fixture: ComponentFixture<PhotoRseDetailComponent>;
    const route = ({ data: of({ photo: new PhotoRse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [PhotoRseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PhotoRseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PhotoRseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.photo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
