import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';

export interface IAlipayFreezeRequestRse {
  id?: number;
  orderTitle?: string;
  outOrderNo?: string;
  outRequestNo?: string;
  payeeUserId?: string;
  payeeLogonId?: string;
  productCode?: string;
  amount?: string;
  extraParam?: string;
  notifyUrl?: string;
  userOrder?: IUserOrderRse;
}

export class AlipayFreezeRequestRse implements IAlipayFreezeRequestRse {
  constructor(
    public id?: number,
    public orderTitle?: string,
    public outOrderNo?: string,
    public outRequestNo?: string,
    public payeeUserId?: string,
    public payeeLogonId?: string,
    public productCode?: string,
    public amount?: string,
    public extraParam?: string,
    public notifyUrl?: string,
    public userOrder?: IUserOrderRse
  ) {}
}
