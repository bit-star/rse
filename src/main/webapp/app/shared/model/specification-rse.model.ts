import { ICommodityRse } from 'app/shared/model/commodity-rse.model';

export interface ISpecificationRse {
  id?: number;
  name?: string;
  value?: string;
  commodity?: ICommodityRse;
}

export class SpecificationRse implements ISpecificationRse {
  constructor(public id?: number, public name?: string, public value?: string, public commodity?: ICommodityRse) {}
}
