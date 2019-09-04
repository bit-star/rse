import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITagRse } from 'app/shared/model/tag-rse.model';

type EntityResponseType = HttpResponse<ITagRse>;
type EntityArrayResponseType = HttpResponse<ITagRse[]>;

@Injectable({ providedIn: 'root' })
export class TagRseService {
  public resourceUrl = SERVER_API_URL + 'api/tags';

  constructor(protected http: HttpClient) {}

  create(tag: ITagRse): Observable<EntityResponseType> {
    return this.http.post<ITagRse>(this.resourceUrl, tag, { observe: 'response' });
  }

  update(tag: ITagRse): Observable<EntityResponseType> {
    return this.http.put<ITagRse>(this.resourceUrl, tag, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITagRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITagRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
