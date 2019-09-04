import { ICommodityRse } from 'app/shared/model/commodity-rse.model';

export interface ITagRse {
  id?: number;
  name?: string;
  icon?: string;
  remark?: string;
  commodity?: ICommodityRse;
}

export class TagRse implements ITagRse {
  constructor(public id?: number, public name?: string, public icon?: string, public remark?: string, public commodity?: ICommodityRse) {}
}
