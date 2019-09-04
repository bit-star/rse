import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IShippingAddressRse } from 'app/shared/model/shipping-address-rse.model';

type EntityResponseType = HttpResponse<IShippingAddressRse>;
type EntityArrayResponseType = HttpResponse<IShippingAddressRse[]>;

@Injectable({ providedIn: 'root' })
export class ShippingAddressRseService {
  public resourceUrl = SERVER_API_URL + 'api/shipping-addresses';

  constructor(protected http: HttpClient) {}

  create(shippingAddress: IShippingAddressRse): Observable<EntityResponseType> {
    return this.http.post<IShippingAddressRse>(this.resourceUrl, shippingAddress, { observe: 'response' });
  }

  update(shippingAddress: IShippingAddressRse): Observable<EntityResponseType> {
    return this.http.put<IShippingAddressRse>(this.resourceUrl, shippingAddress, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IShippingAddressRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IShippingAddressRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
