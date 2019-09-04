import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAlipayFreezeRequestRse, AlipayFreezeRequestRse } from 'app/shared/model/alipay-freeze-request-rse.model';
import { AlipayFreezeRequestRseService } from './alipay-freeze-request-rse.service';
import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';
import { UserOrderRseService } from 'app/entities/user-order-rse';

@Component({
  selector: 'jhi-alipay-freeze-request-rse-update',
  templateUrl: './alipay-freeze-request-rse-update.component.html'
})
export class AlipayFreezeRequestRseUpdateComponent implements OnInit {
  isSaving: boolean;

  userorders: IUserOrderRse[];

  editForm = this.fb.group({
    id: [],
    orderTitle: [],
    outOrderNo: [],
    outRequestNo: [],
    payeeUserId: [],
    payeeLogonId: [],
    productCode: [],
    amount: [],
    extraParam: [],
    notifyUrl: [],
    userOrder: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected alipayFreezeRequestService: AlipayFreezeRequestRseService,
    protected userOrderService: UserOrderRseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ alipayFreezeRequest }) => {
      this.updateForm(alipayFreezeRequest);
    });
    this.userOrderService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUserOrderRse[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUserOrderRse[]>) => response.body)
      )
      .subscribe((res: IUserOrderRse[]) => (this.userorders = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(alipayFreezeRequest: IAlipayFreezeRequestRse) {
    this.editForm.patchValue({
      id: alipayFreezeRequest.id,
      orderTitle: alipayFreezeRequest.orderTitle,
      outOrderNo: alipayFreezeRequest.outOrderNo,
      outRequestNo: alipayFreezeRequest.outRequestNo,
      payeeUserId: alipayFreezeRequest.payeeUserId,
      payeeLogonId: alipayFreezeRequest.payeeLogonId,
      productCode: alipayFreezeRequest.productCode,
      amount: alipayFreezeRequest.amount,
      extraParam: alipayFreezeRequest.extraParam,
      notifyUrl: alipayFreezeRequest.notifyUrl,
      userOrder: alipayFreezeRequest.userOrder
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const alipayFreezeRequest = this.createFromForm();
    if (alipayFreezeRequest.id !== undefined) {
      this.subscribeToSaveResponse(this.alipayFreezeRequestService.update(alipayFreezeRequest));
    } else {
      this.subscribeToSaveResponse(this.alipayFreezeRequestService.create(alipayFreezeRequest));
    }
  }

  private createFromForm(): IAlipayFreezeRequestRse {
    return {
      ...new AlipayFreezeRequestRse(),
      id: this.editForm.get(['id']).value,
      orderTitle: this.editForm.get(['orderTitle']).value,
      outOrderNo: this.editForm.get(['outOrderNo']).value,
      outRequestNo: this.editForm.get(['outRequestNo']).value,
      payeeUserId: this.editForm.get(['payeeUserId']).value,
      payeeLogonId: this.editForm.get(['payeeLogonId']).value,
      productCode: this.editForm.get(['productCode']).value,
      amount: this.editForm.get(['amount']).value,
      extraParam: this.editForm.get(['extraParam']).value,
      notifyUrl: this.editForm.get(['notifyUrl']).value,
      userOrder: this.editForm.get(['userOrder']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlipayFreezeRequestRse>>) {
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

  trackUserOrderById(index: number, item: IUserOrderRse) {
    return item.id;
  }
}
