import { Moment } from 'moment';
import { IOrderItemRse } from 'app/shared/model/order-item-rse.model';

export interface IItemLeaseCycleRse {
  id?: number;
  name?: string;
  startTime?: Moment;
  endTime?: Moment;
  inventory?: number;
  numberOfPeriods?: number;
  deposit?: number;
  creditExemption?: number;
  rentReceivedNumberOfPeriods?: number;
  debitedAmount?: number;
  nextBillDay?: Moment;
  orderItem?: IOrderItemRse;
}

export class ItemLeaseCycleRse implements IItemLeaseCycleRse {
  constructor(
    public id?: number,
    public name?: string,
    public startTime?: Moment,
    public endTime?: Moment,
    public inventory?: number,
    public numberOfPeriods?: number,
    public deposit?: number,
    public creditExemption?: number,
    public rentReceivedNumberOfPeriods?: number,
    public debitedAmount?: number,
    public nextBillDay?: Moment,
    public orderItem?: IOrderItemRse
  ) {}
}
