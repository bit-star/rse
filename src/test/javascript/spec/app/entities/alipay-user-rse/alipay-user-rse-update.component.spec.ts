/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { AlipayUserRseUpdateComponent } from 'app/entities/alipay-user-rse/alipay-user-rse-update.component';
import { AlipayUserRseService } from 'app/entities/alipay-user-rse/alipay-user-rse.service';
import { AlipayUserRse } from 'app/shared/model/alipay-user-rse.model';

describe('Component Tests', () => {
  describe('AlipayUserRse Management Update Component', () => {
    let comp: AlipayUserRseUpdateComponent;
    let fixture: ComponentFixture<AlipayUserRseUpdateComponent>;
    let service: AlipayUserRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [AlipayUserRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AlipayUserRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AlipayUserRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlipayUserRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AlipayUserRse(123);
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
        const entity = new AlipayUserRse();
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
