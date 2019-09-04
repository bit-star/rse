import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IItemLeaseCycleRse, ItemLeaseCycleRse } from 'app/shared/model/item-lease-cycle-rse.model';
import { ItemLeaseCycleRseService } from './item-lease-cycle-rse.service';
import { IOrderItemRse } from 'app/shared/model/order-item-rse.model';
import { OrderItemRseService } from 'app/entities/order-item-rse';

@Component({
  selector: 'jhi-item-lease-cycle-rse-update',
  templateUrl: './item-lease-cycle-rse-update.component.html'
})
export class ItemLeaseCycleRseUpdateComponent implements OnInit {
  isSaving: boolean;

  orderitems: IOrderItemRse[];

  editForm = this.fb.group({
    id: [],
    name: [],
    startTime: [],
    endTime: [],
    inventory: [],
    numberOfPeriods: [],
    deposit: [],
    creditExemption: [],
    rentReceivedNumberOfPeriods: [],
    debitedAmount: [],
    nextBillDay: [],
    orderItem: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected itemLeaseCycleService: ItemLeaseCycleRseService,
    protected orderItemService: OrderItemRseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ itemLeaseCycle }) => {
      this.updateForm(itemLeaseCycle);
    });
    this.orderItemService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOrderItemRse[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOrderItemRse[]>) => response.body)
      )
      .subscribe((res: IOrderItemRse[]) => (this.orderitems = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(itemLeaseCycle: IItemLeaseCycleRse) {
    this.editForm.patchValue({
      id: itemLeaseCycle.id,
      name: itemLeaseCycle.name,
      startTime: itemLeaseCycle.startTime != null ? itemLeaseCycle.startTime.format(DATE_TIME_FORMAT) : null,
      endTime: itemLeaseCycle.endTime != null ? itemLeaseCycle.endTime.format(DATE_TIME_FORMAT) : null,
      inventory: itemLeaseCycle.inventory,
      numberOfPeriods: itemLeaseCycle.numberOfPeriods,
      deposit: itemLeaseCycle.deposit,
      creditExemption: itemLeaseCycle.creditExemption,
      rentReceivedNumberOfPeriods: itemLeaseCycle.rentReceivedNumberOfPeriods,
      debitedAmount: itemLeaseCycle.debitedAmount,
      nextBillDay: itemLeaseCycle.nextBillDay != null ? itemLeaseCycle.nextBillDay.format(DATE_TIME_FORMAT) : null,
      orderItem: itemLeaseCycle.orderItem
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const itemLeaseCycle = this.createFromForm();
    if (itemLeaseCycle.id !== undefined) {
      this.subscribeToSaveResponse(this.itemLeaseCycleService.update(itemLeaseCycle));
    } else {
      this.subscribeToSaveResponse(this.itemLeaseCycleService.create(itemLeaseCycle));
    }
  }

  private createFromForm(): IItemLeaseCycleRse {
    return {
      ...new ItemLeaseCycleRse(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      startTime:
        this.editForm.get(['startTime']).value != null ? moment(this.editForm.get(['startTime']).value, DATE_TIME_FORMAT) : undefined,
      endTime: this.editForm.get(['endTime']).value != null ? moment(this.editForm.get(['endTime']).value, DATE_TIME_FORMAT) : undefined,
      inventory: this.editForm.get(['inventory']).value,
      numberOfPeriods: this.editForm.get(['numberOfPeriods']).value,
      deposit: this.editForm.get(['deposit']).value,
      creditExemption: this.editForm.get(['creditExemption']).value,
      rentReceivedNumberOfPeriods: this.editForm.get(['rentReceivedNumberOfPeriods']).value,
      debitedAmount: this.editForm.get(['debitedAmount']).value,
      nextBillDay:
        this.editForm.get(['nextBillDay']).value != null ? moment(this.editForm.get(['nextBillDay']).value, DATE_TIME_FORMAT) : undefined,
      orderItem: this.editForm.get(['orderItem']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IItemLeaseCycleRse>>) {
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

  trackOrderItemById(index: number, item: IOrderItemRse) {
    return item.id;
  }
}
