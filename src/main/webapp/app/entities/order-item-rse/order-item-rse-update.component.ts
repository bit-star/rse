import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IOrderItemRse, OrderItemRse } from 'app/shared/model/order-item-rse.model';
import { OrderItemRseService } from './order-item-rse.service';
import { ICommodityRse } from 'app/shared/model/commodity-rse.model';
import { CommodityRseService } from 'app/entities/commodity-rse';
import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';
import { UserOrderRseService } from 'app/entities/user-order-rse';

@Component({
  selector: 'jhi-order-item-rse-update',
  templateUrl: './order-item-rse-update.component.html'
})
export class OrderItemRseUpdateComponent implements OnInit {
  isSaving: boolean;

  commodities: ICommodityRse[];

  userorders: IUserOrderRse[];

  editForm = this.fb.group({
    id: [],
    unitPrice: [],
    actuallyPaid: [],
    rightsProtectionStatus: [],
    commodity: [],
    userOrder: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected orderItemService: OrderItemRseService,
    protected commodityService: CommodityRseService,
    protected userOrderService: UserOrderRseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orderItem }) => {
      this.updateForm(orderItem);
    });
    this.commodityService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICommodityRse[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICommodityRse[]>) => response.body)
      )
      .subscribe((res: ICommodityRse[]) => (this.commodities = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.userOrderService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUserOrderRse[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUserOrderRse[]>) => response.body)
      )
      .subscribe((res: IUserOrderRse[]) => (this.userorders = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(orderItem: IOrderItemRse) {
    this.editForm.patchValue({
      id: orderItem.id,
      unitPrice: orderItem.unitPrice,
      actuallyPaid: orderItem.actuallyPaid,
      rightsProtectionStatus: orderItem.rightsProtectionStatus,
      commodity: orderItem.commodity,
      userOrder: orderItem.userOrder
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const orderItem = this.createFromForm();
    if (orderItem.id !== undefined) {
      this.subscribeToSaveResponse(this.orderItemService.update(orderItem));
    } else {
      this.subscribeToSaveResponse(this.orderItemService.create(orderItem));
    }
  }

  private createFromForm(): IOrderItemRse {
    return {
      ...new OrderItemRse(),
      id: this.editForm.get(['id']).value,
      unitPrice: this.editForm.get(['unitPrice']).value,
      actuallyPaid: this.editForm.get(['actuallyPaid']).value,
      rightsProtectionStatus: this.editForm.get(['rightsProtectionStatus']).value,
      commodity: this.editForm.get(['commodity']).value,
      userOrder: this.editForm.get(['userOrder']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderItemRse>>) {
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

  trackCommodityById(index: number, item: ICommodityRse) {
    return item.id;
  }

  trackUserOrderById(index: number, item: IUserOrderRse) {
    return item.id;
  }
}
