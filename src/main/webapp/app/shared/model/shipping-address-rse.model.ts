import { IUser } from 'app/core/user/user.model';

export interface IShippingAddressRse {
  id?: number;
  receiver?: string;
  moblie?: string;
  province?: string;
  city?: string;
  region?: string;
  address?: string;
  user?: IUser;
}

export class ShippingAddressRse implements IShippingAddressRse {
  constructor(
    public id?: number,
    public receiver?: string,
    public moblie?: string,
    public province?: string,
    public city?: string,
    public region?: string,
    public address?: string,
    public user?: IUser
  ) {}
}
