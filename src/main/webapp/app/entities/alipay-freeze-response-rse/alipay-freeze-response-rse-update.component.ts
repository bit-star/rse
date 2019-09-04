import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAlipayFreezeResponseRse, AlipayFreezeResponseRse } from 'app/shared/model/alipay-freeze-response-rse.model';
import { AlipayFreezeResponseRseService } from './alipay-freeze-response-rse.service';
import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';
import { UserOrderRseService } from 'app/entities/user-order-rse';

@Component({
  selector: 'jhi-alipay-freeze-response-rse-update',
  templateUrl: './alipay-freeze-response-rse-update.component.html'
})
export class AlipayFreezeResponseRseUpdateComponent implements OnInit {
  isSaving: boolean;

  userorders: IUserOrderRse[];

  editForm = this.fb.group({
    id: [],
    code: [],
    msg: [],
    subCode: [],
    subMsg: [],
    body: [],
    paramsStr: [],
    amount: [],
    authNo: [],
    creditAmount: [],
    fundAmount: [],
    gmtTrans: [],
    operationId: [],
    outOrderNo: [],
    outRequestNo: [],
    payerUserId: [],
    preAuthType: [],
    status: [],
    userOrder: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected alipayFreezeResponseService: AlipayFreezeResponseRseService,
    protected userOrderService: UserOrderRseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ alipayFreezeResponse }) => {
      this.updateForm(alipayFreezeResponse);
    });
    this.userOrderService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUserOrderRse[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUserOrderRse[]>) => response.body)
      )
      .subscribe((res: IUserOrderRse[]) => (this.userorders = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(alipayFreezeResponse: IAlipayFreezeResponseRse) {
    this.editForm.patchValue({
      id: alipayFreezeResponse.id,
      code: alipayFreezeResponse.code,
      msg: alipayFreezeResponse.msg,
      subCode: alipayFreezeResponse.subCode,
      subMsg: alipayFreezeResponse.subMsg,
      body: alipayFreezeResponse.body,
      paramsStr: alipayFreezeResponse.paramsStr,
      amount: alipayFreezeResponse.amount,
      authNo: alipayFreezeResponse.authNo,
      creditAmount: alipayFreezeResponse.creditAmount,
      fundAmount: alipayFreezeResponse.fundAmount,
      gmtTrans: alipayFreezeResponse.gmtTrans,
      operationId: alipayFreezeResponse.operationId,
      outOrderNo: alipayFreezeResponse.outOrderNo,
      outRequestNo: alipayFreezeResponse.outRequestNo,
      payerUserId: alipayFreezeResponse.payerUserId,
      preAuthType: alipayFreezeResponse.preAuthType,
      status: alipayFreezeResponse.status,
      userOrder: alipayFreezeResponse.userOrder
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const alipayFreezeResponse = this.createFromForm();
    if (alipayFreezeResponse.id !== undefined) {
      this.subscribeToSaveResponse(this.alipayFreezeResponseService.update(alipayFreezeResponse));
    } else {
      this.subscribeToSaveResponse(this.alipayFreezeResponseService.create(alipayFreezeResponse));
    }
  }

  private createFromForm(): IAlipayFreezeResponseRse {
    return {
      ...new AlipayFreezeResponseRse(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      msg: this.editForm.get(['msg']).value,
      subCode: this.editForm.get(['subCode']).value,
      subMsg: this.editForm.get(['subMsg']).value,
      body: this.editForm.get(['body']).value,
      paramsStr: this.editForm.get(['paramsStr']).value,
      amount: this.editForm.get(['amount']).value,
      authNo: this.editForm.get(['authNo']).value,
      creditAmount: this.editForm.get(['creditAmount']).value,
      fundAmount: this.editForm.get(['fundAmount']).value,
      gmtTrans: this.editForm.get(['gmtTrans']).value,
      operationId: this.editForm.get(['operationId']).value,
      outOrderNo: this.editForm.get(['outOrderNo']).value,
      outRequestNo: this.editForm.get(['outRequestNo']).value,
      payerUserId: this.editForm.get(['payerUserId']).value,
      preAuthType: this.editForm.get(['preAuthType']).value,
      status: this.editForm.get(['status']).value,
      userOrder: this.editForm.get(['userOrder']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlipayFreezeResponseRse>>) {
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
