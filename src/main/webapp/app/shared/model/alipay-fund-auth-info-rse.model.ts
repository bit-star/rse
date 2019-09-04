import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';

export interface IAlipayFundAuthInfoRse {
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
  extraParam?: string;
  fundAmount?: string;
  gmtCreateStr?: string;
  gmtTransStr?: string;
  operationId?: string;
  operationType?: string;
  orderTitle?: string;
  outOrderNo?: string;
  outRequestNo?: string;
  payerLogonId?: string;
  payerUserId?: string;
  preAuthType?: string;
  remark?: string;
  restAmount?: string;
  restCreditAmount?: string;
  restFundAmount?: string;
  status?: string;
  totalFreezeAmount?: string;
  totalFreezeCreditAmount?: string;
  totalFreezeFundAmount?: string;
  totalPayAmount?: string;
  totalPayCreditAmount?: string;
  totalPayFundAmount?: string;
  transCurrency?: string;
  userOrder?: IUserOrderRse;
}

export class AlipayFundAuthInfoRse implements IAlipayFundAuthInfoRse {
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
    public extraParam?: string,
    public fundAmount?: string,
    public gmtCreateStr?: string,
    public gmtTransStr?: string,
    public operationId?: string,
    public operationType?: string,
    public orderTitle?: string,
    public outOrderNo?: string,
    public outRequestNo?: string,
    public payerLogonId?: string,
    public payerUserId?: string,
    public preAuthType?: string,
    public remark?: string,
    public restAmount?: string,
    public restCreditAmount?: string,
    public restFundAmount?: string,
    public status?: string,
    public totalFreezeAmount?: string,
    public totalFreezeCreditAmount?: string,
    public totalFreezeFundAmount?: string,
    public totalPayAmount?: string,
    public totalPayCreditAmount?: string,
    public totalPayFundAmount?: string,
    public transCurrency?: string,
    public userOrder?: IUserOrderRse
  ) {}
}
