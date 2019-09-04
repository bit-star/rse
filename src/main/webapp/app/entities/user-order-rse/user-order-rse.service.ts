import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';

type EntityResponseType = HttpResponse<IUserOrderRse>;
type EntityArrayResponseType = HttpResponse<IUserOrderRse[]>;

@Injectable({ providedIn: 'root' })
export class UserOrderRseService {
  public resourceUrl = SERVER_API_URL + 'api/user-orders';

  constructor(protected http: HttpClient) {}

  create(userOrder: IUserOrderRse): Observable<EntityResponseType> {
    return this.http.post<IUserOrderRse>(this.resourceUrl, userOrder, { observe: 'response' });
  }

  update(userOrder: IUserOrderRse): Observable<EntityResponseType> {
    return this.http.put<IUserOrderRse>(this.resourceUrl, userOrder, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserOrderRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserOrderRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
