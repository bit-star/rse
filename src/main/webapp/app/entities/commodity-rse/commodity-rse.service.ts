import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICommodityRse } from 'app/shared/model/commodity-rse.model';

type EntityResponseType = HttpResponse<ICommodityRse>;
type EntityArrayResponseType = HttpResponse<ICommodityRse[]>;

@Injectable({ providedIn: 'root' })
export class CommodityRseService {
  public resourceUrl = SERVER_API_URL + 'api/commodities';

  constructor(protected http: HttpClient) {}

  create(commodity: ICommodityRse): Observable<EntityResponseType> {
    return this.http.post<ICommodityRse>(this.resourceUrl, commodity, { observe: 'response' });
  }

  update(commodity: ICommodityRse): Observable<EntityResponseType> {
    return this.http.put<ICommodityRse>(this.resourceUrl, commodity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICommodityRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICommodityRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
