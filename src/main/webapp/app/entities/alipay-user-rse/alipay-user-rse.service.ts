import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAlipayUserRse } from 'app/shared/model/alipay-user-rse.model';

type EntityResponseType = HttpResponse<IAlipayUserRse>;
type EntityArrayResponseType = HttpResponse<IAlipayUserRse[]>;

@Injectable({ providedIn: 'root' })
export class AlipayUserRseService {
  public resourceUrl = SERVER_API_URL + 'api/alipay-users';

  constructor(protected http: HttpClient) {}

  create(alipayUser: IAlipayUserRse): Observable<EntityResponseType> {
    return this.http.post<IAlipayUserRse>(this.resourceUrl, alipayUser, { observe: 'response' });
  }

  update(alipayUser: IAlipayUserRse): Observable<EntityResponseType> {
    return this.http.put<IAlipayUserRse>(this.resourceUrl, alipayUser, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAlipayUserRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAlipayUserRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
