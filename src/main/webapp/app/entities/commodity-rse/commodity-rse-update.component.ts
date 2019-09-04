import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICommodityRse, CommodityRse } from 'app/shared/model/commodity-rse.model';
import { CommodityRseService } from './commodity-rse.service';
import { ICategoryRse } from 'app/shared/model/category-rse.model';
import { CategoryRseService } from 'app/entities/category-rse';

@Component({
  selector: 'jhi-commodity-rse-update',
  templateUrl: './commodity-rse-update.component.html'
})
export class CommodityRseUpdateComponent implements OnInit {
  isSaving: boolean;

  categories: ICategoryRse[];

  editForm = this.fb.group({
    id: [],
    name: [],
    remark: [],
    dayRent: [],
    weeklyRent: [],
    monthlyRent: [],
    deposit: [],
    rentalMethod: [],
    maxRent: [],
    inventory: [],
    deliveryMethod: [],
    status: [],
    leaseMustRead: [],
    desciption: [],
    category: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected commodityService: CommodityRseService,
    protected categoryService: CategoryRseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ commodity }) => {
      this.updateForm(commodity);
    });
    this.categoryService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICategoryRse[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICategoryRse[]>) => response.body)
      )
      .subscribe((res: ICategoryRse[]) => (this.categories = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(commodity: ICommodityRse) {
    this.editForm.patchValue({
      id: commodity.id,
      name: commodity.name,
      remark: commodity.remark,
      dayRent: commodity.dayRent,
      weeklyRent: commodity.weeklyRent,
      monthlyRent: commodity.monthlyRent,
      deposit: commodity.deposit,
      rentalMethod: commodity.rentalMethod,
      maxRent: commodity.maxRent,
      inventory: commodity.inventory,
      deliveryMethod: commodity.deliveryMethod,
      status: commodity.status,
      leaseMustRead: commodity.leaseMustRead,
      desciption: commodity.desciption,
      category: commodity.category
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const commodity = this.createFromForm();
    if (commodity.id !== undefined) {
      this.subscribeToSaveResponse(this.commodityService.update(commodity));
    } else {
      this.subscribeToSaveResponse(this.commodityService.create(commodity));
    }
  }

  private createFromForm(): ICommodityRse {
    return {
      ...new CommodityRse(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      remark: this.editForm.get(['remark']).value,
      dayRent: this.editForm.get(['dayRent']).value,
      weeklyRent: this.editForm.get(['weeklyRent']).value,
      monthlyRent: this.editForm.get(['monthlyRent']).value,
      deposit: this.editForm.get(['deposit']).value,
      rentalMethod: this.editForm.get(['rentalMethod']).value,
      maxRent: this.editForm.get(['maxRent']).value,
      inventory: this.editForm.get(['inventory']).value,
      deliveryMethod: this.editForm.get(['deliveryMethod']).value,
      status: this.editForm.get(['status']).value,
      leaseMustRead: this.editForm.get(['leaseMustRead']).value,
      desciption: this.editForm.get(['desciption']).value,
      category: this.editForm.get(['category']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommodityRse>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackCategoryById(index: number, item: ICategoryRse) {
    return item.id;
  }
}
