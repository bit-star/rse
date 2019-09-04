/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { AlipayFreezeResponseRseUpdateComponent } from 'app/entities/alipay-freeze-response-rse/alipay-freeze-response-rse-update.component';
import { AlipayFreezeResponseRseService } from 'app/entities/alipay-freeze-response-rse/alipay-freeze-response-rse.service';
import { AlipayFreezeResponseRse } from 'app/shared/model/alipay-freeze-response-rse.model';

describe('Component Tests', () => {
  describe('AlipayFreezeResponseRse Management Update Component', () => {
    let comp: AlipayFreezeResponseRseUpdateComponent;
    let fixture: ComponentFixture<AlipayFreezeResponseRseUpdateComponent>;
    let service: AlipayFreezeResponseRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayFreezeResponseRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AlipayFreezeResponseRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AlipayFreezeResponseRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlipayFreezeResponseRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AlipayFreezeResponseRse(123);
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
        const entity = new AlipayFreezeResponseRse();
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
