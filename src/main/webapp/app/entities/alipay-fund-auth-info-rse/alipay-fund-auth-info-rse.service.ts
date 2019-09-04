import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAlipayFundAuthInfoRse } from 'app/shared/model/alipay-fund-auth-info-rse.model';

type EntityResponseType = HttpResponse<IAlipayFundAuthInfoRse>;
type EntityArrayResponseType = HttpResponse<IAlipayFundAuthInfoRse[]>;

@Injectable({ providedIn: 'root' })
export class AlipayFundAuthInfoRseService {
  public resourceUrl = SERVER_API_URL + 'api/alipay-fund-auth-infos';

  constructor(protected http: HttpClient) {}

  create(alipayFundAuthInfo: IAlipayFundAuthInfoRse): Observable<EntityResponseType> {
    return this.http.post<IAlipayFundAuthInfoRse>(this.resourceUrl, alipayFundAuthInfo, { observe: 'response' });
  }

  update(alipayFundAuthInfo: IAlipayFundAuthInfoRse): Observable<EntityResponseType> {
    return this.http.put<IAlipayFundAuthInfoRse>(this.resourceUrl, alipayFundAuthInfo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAlipayFundAuthInfoRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAlipayFundAuthInfoRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
