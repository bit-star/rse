/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { CommodityRseUpdateComponent } from 'app/entities/commodity-rse/commodity-rse-update.component';
import { CommodityRseService } from 'app/entities/commodity-rse/commodity-rse.service';
import { CommodityRse } from 'app/shared/model/commodity-rse.model';

describe('Component Tests', () => {
  describe('CommodityRse Management Update Component', () => {
    let comp: CommodityRseUpdateComponent;
    let fixture: ComponentFixture<CommodityRseUpdateComponent>;
    let service: CommodityRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [CommodityRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CommodityRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommodityRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommodityRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommodityRse(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommodityRse();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
