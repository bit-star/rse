export interface IAlipayUserRse {
  id?: number;
  userId?: string;
  avatar?: string;
  nickName?: string;
  mobile?: string;
  gender?: string;
  countryCode?: string;
  province?: string;
  city?: string;
  accessToken?: string;
  authTokenType?: string;
  expiresIn?: string;
  alipayUserId?: string;
  reExpiresIn?: string;
  refreshToken?: string;
}

export class AlipayUserRse implements IAlipayUserRse {
  constructor(
    public id?: number,
    public userId?: string,
    public avatar?: string,
    public nickName?: string,
    public mobile?: string,
    public gender?: string,
    public countryCode?: string,
    public province?: string,
    public city?: string,
    public accessToken?: string,
    public authTokenType?: string,
    public expiresIn?: string,
    public alipayUserId?: string,
    public reExpiresIn?: string,
    public refreshToken?: string
  ) {}
}
