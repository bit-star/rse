import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAlipayFreezeResponseRse } from 'app/shared/model/alipay-freeze-response-rse.model';

type EntityResponseType = HttpResponse<IAlipayFreezeResponseRse>;
type EntityArrayResponseType = HttpResponse<IAlipayFreezeResponseRse[]>;

@Injectable({ providedIn: 'root' })
export class AlipayFreezeResponseRseService {
  public resourceUrl = SERVER_API_URL + 'api/alipay-freeze-responses';

  constructor(protected http: HttpClient) {}

  create(alipayFreezeResponse: IAlipayFreezeResponseRse): Observable<EntityResponseType> {
    return this.http.post<IAlipayFreezeResponseRse>(this.resourceUrl, alipayFreezeResponse, { observe: 'response' });
  }

  update(alipayFreezeResponse: IAlipayFreezeResponseRse): Observable<EntityResponseType> {
    return this.http.put<IAlipayFreezeResponseRse>(this.resourceUrl, alipayFreezeResponse, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAlipayFreezeResponseRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAlipayFreezeResponseRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
