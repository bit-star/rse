import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAlipayFreezeRequestRse } from 'app/shared/model/alipay-freeze-request-rse.model';

type EntityResponseType = HttpResponse<IAlipayFreezeRequestRse>;
type EntityArrayResponseType = HttpResponse<IAlipayFreezeRequestRse[]>;

@Injectable({ providedIn: 'root' })
export class AlipayFreezeRequestRseService {
  public resourceUrl = SERVER_API_URL + 'api/alipay-freeze-requests';

  constructor(protected http: HttpClient) {}

  create(alipayFreezeRequest: IAlipayFreezeRequestRse): Observable<EntityResponseType> {
    return this.http.post<IAlipayFreezeRequestRse>(this.resourceUrl, alipayFreezeRequest, { observe: 'response' });
  }

  update(alipayFreezeRequest: IAlipayFreezeRequestRse): Observable<EntityResponseType> {
    return this.http.put<IAlipayFreezeRequestRse>(this.resourceUrl, alipayFreezeRequest, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAlipayFreezeRequestRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAlipayFreezeRequestRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
