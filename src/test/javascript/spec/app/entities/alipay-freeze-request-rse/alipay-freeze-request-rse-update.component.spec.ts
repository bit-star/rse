/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { AlipayFreezeRequestRseUpdateComponent } from 'app/entities/alipay-freeze-request-rse/alipay-freeze-request-rse-update.component';
import { AlipayFreezeRequestRseService } from 'app/entities/alipay-freeze-request-rse/alipay-freeze-request-rse.service';
import { AlipayFreezeRequestRse } from 'app/shared/model/alipay-freeze-request-rse.model';

describe('Component Tests', () => {
  describe('AlipayFreezeRequestRse Management Update Component', () => {
    let comp: AlipayFreezeRequestRseUpdateComponent;
    let fixture: ComponentFixture<AlipayFreezeRequestRseUpdateComponent>;
    let service: AlipayFreezeRequestRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayFreezeRequestRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AlipayFreezeRequestRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AlipayFreezeRequestRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlipayFreezeRequestRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AlipayFreezeRequestRse(123);
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
        const entity = new AlipayFreezeRequestRse();
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
