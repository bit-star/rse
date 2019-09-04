import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAlipayFundAuthInfoRse, AlipayFundAuthInfoRse } from 'app/shared/model/alipay-fund-auth-info-rse.model';
import { AlipayFundAuthInfoRseService } from './alipay-fund-auth-info-rse.service';
import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';
import { UserOrderRseService } from 'app/entities/user-order-rse';

@Component({
  selector: 'jhi-alipay-fund-auth-info-rse-update',
  templateUrl: './alipay-fund-auth-info-rse-update.component.html'
})
export class AlipayFundAuthInfoRseUpdateComponent implements OnInit {
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
    extraParam: [],
    fundAmount: [],
    gmtCreateStr: [],
    gmtTransStr: [],
    operationId: [],
    operationType: [],
    orderTitle: [],
    outOrderNo: [],
    outRequestNo: [],
    payerLogonId: [],
    payerUserId: [],
    preAuthType: [],
    remark: [],
    restAmount: [],
    restCreditAmount: [],
    restFundAmount: [],
    status: [],
    totalFreezeAmount: [],
    totalFreezeCreditAmount: [],
    totalFreezeFundAmount: [],
    totalPayAmount: [],
    totalPayCreditAmount: [],
    totalPayFundAmount: [],
    transCurrency: [],
    userOrder: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected alipayFundAuthInfoService: AlipayFundAuthInfoRseService,
    protected userOrderService: UserOrderRseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ alipayFundAuthInfo }) => {
      this.updateForm(alipayFundAuthInfo);
    });
    this.userOrderService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUserOrderRse[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUserOrderRse[]>) => response.body)
      )
      .subscribe((res: IUserOrderRse[]) => (this.userorders = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(alipayFundAuthInfo: IAlipayFundAuthInfoRse) {
    this.editForm.patchValue({
      id: alipayFundAuthInfo.id,
      code: alipayFundAuthInfo.code,
      msg: alipayFundAuthInfo.msg,
      subCode: alipayFundAuthInfo.subCode,
      subMsg: alipayFundAuthInfo.subMsg,
      body: alipayFundAuthInfo.body,
      paramsStr: alipayFundAuthInfo.paramsStr,
      amount: alipayFundAuthInfo.amount,
      authNo: alipayFundAuthInfo.authNo,
      creditAmount: alipayFundAuthInfo.creditAmount,
      extraParam: alipayFundAuthInfo.extraParam,
      fundAmount: alipayFundAuthInfo.fundAmount,
      gmtCreateStr: alipayFundAuthInfo.gmtCreateStr,
      gmtTransStr: alipayFundAuthInfo.gmtTransStr,
      operationId: alipayFundAuthInfo.operationId,
      operationType: alipayFundAuthInfo.operationType,
      orderTitle: alipayFundAuthInfo.orderTitle,
      outOrderNo: alipayFundAuthInfo.outOrderNo,
      outRequestNo: alipayFundAuthInfo.outRequestNo,
      payerLogonId: alipayFundAuthInfo.payerLogonId,
      payerUserId: alipayFundAuthInfo.payerUserId,
      preAuthType: alipayFundAuthInfo.preAuthType,
      remark: alipayFundAuthInfo.remark,
      restAmount: alipayFundAuthInfo.restAmount,
      restCreditAmount: alipayFundAuthInfo.restCreditAmount,
      restFundAmount: alipayFundAuthInfo.restFundAmount,
      status: alipayFundAuthInfo.status,
      totalFreezeAmount: alipayFundAuthInfo.totalFreezeAmount,
      totalFreezeCreditAmount: alipayFundAuthInfo.totalFreezeCreditAmount,
      totalFreezeFundAmount: alipayFundAuthInfo.totalFreezeFundAmount,
      totalPayAmount: alipayFundAuthInfo.totalPayAmount,
      totalPayCreditAmount: alipayFundAuthInfo.totalPayCreditAmount,
      totalPayFundAmount: alipayFundAuthInfo.totalPayFundAmount,
      transCurrency: alipayFundAuthInfo.transCurrency,
      userOrder: alipayFundAuthInfo.userOrder
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const alipayFundAuthInfo = this.createFromForm();
    if (alipayFundAuthInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.alipayFundAuthInfoService.update(alipayFundAuthInfo));
    } else {
      this.subscribeToSaveResponse(this.alipayFundAuthInfoService.create(alipayFundAuthInfo));
    }
  }

  private createFromForm(): IAlipayFundAuthInfoRse {
    return {
      ...new AlipayFundAuthInfoRse(),
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
      extraParam: this.editForm.get(['extraParam']).value,
      fundAmount: this.editForm.get(['fundAmount']).value,
      gmtCreateStr: this.editForm.get(['gmtCreateStr']).value,
      gmtTransStr: this.editForm.get(['gmtTransStr']).value,
      operationId: this.editForm.get(['operationId']).value,
      operationType: this.editForm.get(['operationType']).value,
      orderTitle: this.editForm.get(['orderTitle']).value,
      outOrderNo: this.editForm.get(['outOrderNo']).value,
      outRequestNo: this.editForm.get(['outRequestNo']).value,
      payerLogonId: this.editForm.get(['payerLogonId']).value,
      payerUserId: this.editForm.get(['payerUserId']).value,
      preAuthType: this.editForm.get(['preAuthType']).value,
      remark: this.editForm.get(['remark']).value,
      restAmount: this.editForm.get(['restAmount']).value,
      restCreditAmount: this.editForm.get(['restCreditAmount']).value,
      restFundAmount: this.editForm.get(['restFundAmount']).value,
      status: this.editForm.get(['status']).value,
      totalFreezeAmount: this.editForm.get(['totalFreezeAmount']).value,
      totalFreezeCreditAmount: this.editForm.get(['totalFreezeCreditAmount']).value,
      totalFreezeFundAmount: this.editForm.get(['totalFreezeFundAmount']).value,
      totalPayAmount: this.editForm.get(['totalPayAmount']).value,
      totalPayCreditAmount: this.editForm.get(['totalPayCreditAmount']).value,
      totalPayFundAmount: this.editForm.get(['totalPayFundAmount']).value,
      transCurrency: this.editForm.get(['transCurrency']).value,
      userOrder: this.editForm.get(['userOrder']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlipayFundAuthInfoRse>>) {
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
