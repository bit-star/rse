import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICategoryRse } from 'app/shared/model/category-rse.model';

type EntityResponseType = HttpResponse<ICategoryRse>;
type EntityArrayResponseType = HttpResponse<ICategoryRse[]>;

@Injectable({ providedIn: 'root' })
export class CategoryRseService {
  public resourceUrl = SERVER_API_URL + 'api/categories';

  constructor(protected http: HttpClient) {}

  create(category: ICategoryRse): Observable<EntityResponseType> {
    return this.http.post<ICategoryRse>(this.resourceUrl, category, { observe: 'response' });
  }

  update(category: ICategoryRse): Observable<EntityResponseType> {
    return this.http.put<ICategoryRse>(this.resourceUrl, category, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategoryRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategoryRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
