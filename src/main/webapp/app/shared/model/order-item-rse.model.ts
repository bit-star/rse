import { IItemLeaseCycleRse } from 'app/shared/model/item-lease-cycle-rse.model';
import { ICommodityRse } from 'app/shared/model/commodity-rse.model';
import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';

export const enum RightsProtectionStatus {
  Normal = 'Normal',
  Objection = 'Objection'
}

export interface IOrderItemRse {
  id?: number;
  unitPrice?: number;
  actuallyPaid?: number;
  rightsProtectionStatus?: RightsProtectionStatus;
  itemLeaseCycles?: IItemLeaseCycleRse[];
  commodity?: ICommodityRse;
  userOrder?: IUserOrderRse;
}

export class OrderItemRse implements IOrderItemRse {
  constructor(
    public id?: number,
    public unitPrice?: number,
    public actuallyPaid?: number,
    public rightsProtectionStatus?: RightsProtectionStatus,
    public itemLeaseCycles?: IItemLeaseCycleRse[],
    public commodity?: ICommodityRse,
    public userOrder?: IUserOrderRse
  ) {}
}
