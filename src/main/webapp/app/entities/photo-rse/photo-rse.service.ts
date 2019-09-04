import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPhotoRse } from 'app/shared/model/photo-rse.model';

type EntityResponseType = HttpResponse<IPhotoRse>;
type EntityArrayResponseType = HttpResponse<IPhotoRse[]>;

@Injectable({ providedIn: 'root' })
export class PhotoRseService {
  public resourceUrl = SERVER_API_URL + 'api/photos';

  constructor(protected http: HttpClient) {}

  create(photo: IPhotoRse): Observable<EntityResponseType> {
    return this.http.post<IPhotoRse>(this.resourceUrl, photo, { observe: 'response' });
  }

  update(photo: IPhotoRse): Observable<EntityResponseType> {
    return this.http.put<IPhotoRse>(this.resourceUrl, photo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPhotoRse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPhotoRse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
