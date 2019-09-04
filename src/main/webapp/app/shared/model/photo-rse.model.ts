import { ICommodityRse } from 'app/shared/model/commodity-rse.model';

export interface IPhotoRse {
  id?: number;
  originalImage?: string;
  thumbnail?: string;
  width?: number;
  height?: number;
  rankOrder?: number;
  commodity?: ICommodityRse;
}

export class PhotoRse implements IPhotoRse {
  constructor(
    public id?: number,
    public originalImage?: string,
    public thumbnail?: string,
    public width?: number,
    public height?: number,
    public rankOrder?: number,
    public commodity?: ICommodityRse
  ) {}
}
