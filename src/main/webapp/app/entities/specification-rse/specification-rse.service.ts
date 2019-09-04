import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISpecificationRse } from 'app/shared/model/specification-rse.model';

type EntityResponseType = HttpResponse<ISpecificationRse>;
type EntityArrayResponseType = HttpResponse<ISpecificationRse[]>;

@Injectable({ providedIn: 'root' })
export class SpecificationRseService {
  public resourceUrl = SERVER_API_URL + 'api/specifications';

  constructor(protected http: HttpClient) {}

  create(specification: ISpecificationRse): Observable<EntityResponseType> {
    return this.http.post<ISpecificationRse>(this.resourceUrl, specification, { observe: 'response' });
  }

  update(specification: ISpecificationRse): Observable<EntityResponseType> {
    return this.http.put<ISpecificationRse>(this.resourceUrl, specification, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISpecificationRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISpecificationRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
