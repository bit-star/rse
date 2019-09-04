import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrderItemRse } from 'app/shared/model/order-item-rse.model';

type EntityResponseType = HttpResponse<IOrderItemRse>;
type EntityArrayResponseType = HttpResponse<IOrderItemRse[]>;

@Injectable({ providedIn: 'root' })
export class OrderItemRseService {
  public resourceUrl = SERVER_API_URL + 'api/order-items';

  constructor(protected http: HttpClient) {}

  create(orderItem: IOrderItemRse): Observable<EntityResponseType> {
    return this.http.post<IOrderItemRse>(this.resourceUrl, orderItem, { observe: 'response' });
  }

  update(orderItem: IOrderItemRse): Observable<EntityResponseType> {
    return this.http.put<IOrderItemRse>(this.resourceUrl, orderItem, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrderItemRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrderItemRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
