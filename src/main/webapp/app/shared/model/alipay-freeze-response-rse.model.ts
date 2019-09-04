import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';

export interface IAlipayFreezeResponseRse {
  id?: number;
  code?: string;
  msg?: string;
  subCode?: string;
  subMsg?: string;
  body?: string;
  paramsStr?: string;
  amount?: string;
  authNo?: string;
  creditAmount?: string;
  fundAmount?: string;
  gmtTrans?: string;
  operationId?: string;
  outOrderNo?: string;
  outRequestNo?: string;
  payerUserId?: string;
  preAuthType?: string;
  status?: string;
  userOrder?: IUserOrderRse;
}

export class AlipayFreezeResponseRse implements IAlipayFreezeResponseRse {
  constructor(
    public id?: number,
    public code?: string,
    public msg?: string,
    public subCode?: string,
    public subMsg?: string,
    public body?: string,
    public paramsStr?: string,
    public amount?: string,
    public authNo?: string,
    public creditAmount?: string,
    public fundAmount?: string,
    public gmtTrans?: string,
    public operationId?: string,
    public outOrderNo?: string,
    public outRequestNo?: string,
    public payerUserId?: string,
    public preAuthType?: string,
    public status?: string,
    public userOrder?: IUserOrderRse
  ) {}
}
