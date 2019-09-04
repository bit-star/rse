import { ICommodityRse } from 'app/shared/model/commodity-rse.model';

export interface ICategoryRse {
  id?: number;
  name?: string;
  order?: number;
  commodities?: ICommodityRse[];
}

export class CategoryRse implements ICategoryRse {
  constructor(public id?: number, public name?: string, public order?: number, public commodities?: ICommodityRse[]) {}
}
