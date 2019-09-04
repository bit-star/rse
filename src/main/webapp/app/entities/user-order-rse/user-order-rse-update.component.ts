import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IUserOrderRse, UserOrderRse } from 'app/shared/model/user-order-rse.model';
import { UserOrderRseService } from './user-order-rse.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-user-order-rse-update',
  templateUrl: './user-order-rse-update.component.html'
})
export class UserOrderRseUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    orderNo: [],
    requestNo: [],
    status: [],
    receiver: [],
    mobile: [],
    province: [],
    city: [],
    region: [],
    address: [],
    storeName: [],
    logisticsCompany: [],
    shipmentNumber: [],
    paymentMethod: [],
    freight: [],
    description: [],
    processingOpinions: [],
    user: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected userOrderService: UserOrderRseService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ userOrder }) => {
      this.updateForm(userOrder);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(userOrder: IUserOrderRse) {
    this.editForm.patchValue({
      id: userOrder.id,
      orderNo: userOrder.orderNo,
      requestNo: userOrder.requestNo,
      status: userOrder.status,
      receiver: userOrder.receiver,
      mobile: userOrder.mobile,
      province: userOrder.province,
      city: userOrder.city,
      region: userOrder.region,
      address: userOrder.address,
      storeName: userOrder.storeName,
      logisticsCompany: userOrder.logisticsCompany,
      shipmentNumber: userOrder.shipmentNumber,
      paymentMethod: userOrder.paymentMethod,
      freight: userOrder.freight,
      description: userOrder.description,
      processingOpinions: userOrder.processingOpinions,
      user: userOrder.user
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const userOrder = this.createFromForm();
    if (userOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.userOrderService.update(userOrder));
    } else {
      this.subscribeToSaveResponse(this.userOrderService.create(userOrder));
    }
  }

  private createFromForm(): IUserOrderRse {
    return {
      ...new UserOrderRse(),
      id: this.editForm.get(['id']).value,
      orderNo: this.editForm.get(['orderNo']).value,
      requestNo: this.editForm.get(['requestNo']).value,
      status: this.editForm.get(['status']).value,
      receiver: this.editForm.get(['receiver']).value,
      mobile: this.editForm.get(['mobile']).value,
      province: this.editForm.get(['province']).value,
      city: this.editForm.get(['city']).value,
      region: this.editForm.get(['region']).value,
      address: this.editForm.get(['address']).value,
      storeName: this.editForm.get(['storeName']).value,
      logisticsCompany: this.editForm.get(['logisticsCompany']).value,
      shipmentNumber: this.editForm.get(['shipmentNumber']).value,
      paymentMethod: this.editForm.get(['paymentMethod']).value,
      freight: this.editForm.get(['freight']).value,
      description: this.editForm.get(['description']).value,
      processingOpinions: this.editForm.get(['processingOpinions']).value,
      user: this.editForm.get(['user']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserOrderRse>>) {
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

  trackUserById(index: number, item: IUser) {
    return item.id;
  }
}
