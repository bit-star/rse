import { IPhotoRse } from 'app/shared/model/photo-rse.model';
import { ISpecificationRse } from 'app/shared/model/specification-rse.model';
import { ITagRse } from 'app/shared/model/tag-rse.model';
import { ICategoryRse } from 'app/shared/model/category-rse.model';
import { IOrderItemRse } from 'app/shared/model/order-item-rse.model';

export const enum DeliveryMethod {
  ExpressDelivery = 'ExpressDelivery',
  PickUp = 'PickUp'
}

export const enum CommodityStatus {
  OnLine = 'OnLine',
  OffLine = 'OffLine'
}

export interface ICommodityRse {
  id?: number;
  name?: string;
  remark?: string;
  dayRent?: number;
  weeklyRent?: number;
  monthlyRent?: number;
  deposit?: number;
  rentalMethod?: string;
  maxRent?: number;
  inventory?: number;
  deliveryMethod?: DeliveryMethod;
  status?: CommodityStatus;
  leaseMustRead?: string;
  desciption?: string;
  photos?: IPhotoRse[];
  specifications?: ISpecificationRse[];
  tags?: ITagRse[];
  category?: ICategoryRse;
  orderItems?: IOrderItemRse[];
}

export class CommodityRse implements ICommodityRse {
  constructor(
    public id?: number,
    public name?: string,
    public remark?: string,
    public dayRent?: number,
    public weeklyRent?: number,
    public monthlyRent?: number,
    public deposit?: number,
    public rentalMethod?: string,
    public maxRent?: number,
    public inventory?: number,
    public deliveryMethod?: DeliveryMethod,
    public status?: CommodityStatus,
    public leaseMustRead?: string,
    public desciption?: string,
    public photos?: IPhotoRse[],
    public specifications?: ISpecificationRse[],
    public tags?: ITagRse[],
    public category?: ICategoryRse,
    public orderItems?: IOrderItemRse[]
  ) {}
}
