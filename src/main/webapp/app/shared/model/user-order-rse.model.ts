import { IOrderItemRse } from 'app/shared/model/order-item-rse.model';
import { IAlipayFreezeRequestRse } from 'app/shared/model/alipay-freeze-request-rse.model';
import { IAlipayFreezeResponseRse } from 'app/shared/model/alipay-freeze-response-rse.model';
import { IAlipayFundAuthInfoRse } from 'app/shared/model/alipay-fund-auth-info-rse.model';
import { IUser } from 'app/core/user/user.model';

export const enum OrderStatus {
  PendingPayment = 'PendingPayment',
  PendingConfirming = 'PendingConfirming',
  Stocking = 'Stocking',
  Shipped = 'Shipped',
  Arrived = 'Arrived',
  ToBeReturned = 'ToBeReturned',
  Returning = 'Returning',
  Completed = 'Completed',
  BeExpired = 'BeExpired',
  HasBoughtOut = 'HasBoughtOut',
  Closed = 'Closed'
}

export interface IUserOrderRse {
  id?: number;
  orderNo?: string;
  requestNo?: string;
  status?: OrderStatus;
  receiver?: string;
  mobile?: string;
  province?: string;
  city?: string;
  region?: string;
  address?: string;
  storeName?: string;
  logisticsCompany?: string;
  shipmentNumber?: string;
  paymentMethod?: string;
  freight?: number;
  description?: string;
  processingOpinions?: string;
  orderItems?: IOrderItemRse[];
  alipayFreezeRequests?: IAlipayFreezeRequestRse[];
  alipayFreezeResponses?: IAlipayFreezeResponseRse[];
  alipayFundAuthInfos?: IAlipayFundAuthInfoRse[];
  user?: IUser;
}

export class UserOrderRse implements IUserOrderRse {
  constructor(
    public id?: number,
    public orderNo?: string,
    public requestNo?: string,
    public status?: OrderStatus,
    public receiver?: string,
    public mobile?: string,
    public province?: string,
    public city?: string,
    public region?: string,
    public address?: string,
    public storeName?: string,
    public logisticsCompany?: string,
    public shipmentNumber?: string,
    public paymentMethod?: string,
    public freight?: number,
    public description?: string,
    public processingOpinions?: string,
    public orderItems?: IOrderItemRse[],
    public alipayFreezeRequests?: IAlipayFreezeRequestRse[],
    public alipayFreezeResponses?: IAlipayFreezeResponseRse[],
    public alipayFundAuthInfos?: IAlipayFundAuthInfoRse[],
    public user?: IUser
  ) {}
}
