/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { RseTestModule } from '../../../test.module';
import { UserOrderRseUpdateComponent } from 'app/entities/user-order-rse/user-order-rse-update.component';
import { UserOrderRseService } from 'app/entities/user-order-rse/user-order-rse.service';
import { UserOrderRse } from 'app/shared/model/user-order-rse.model';

describe('Component Tests', () => {
  describe('UserOrderRse Management Update Component', () => {
    let comp: UserOrderRseUpdateComponent;
    let fixture: ComponentFixture<UserOrderRseUpdateComponent>;
    let service: UserOrderRseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RseTestModule],
        declarations: [UserOrderRseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UserOrderRseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserOrderRseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserOrderRseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserOrderRse(123);
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
        const entity = new UserOrderRse();
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
