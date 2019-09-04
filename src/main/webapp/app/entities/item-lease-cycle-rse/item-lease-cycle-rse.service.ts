import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IItemLeaseCycleRse } from 'app/shared/model/item-lease-cycle-rse.model';

type EntityResponseType = HttpResponse<IItemLeaseCycleRse>;
type EntityArrayResponseType = HttpResponse<IItemLeaseCycleRse[]>;

@Injectable({ providedIn: 'root' })
export class ItemLeaseCycleRseService {
  public resourceUrl = SERVER_API_URL + 'api/item-lease-cycles';

  constructor(protected http: HttpClient) {}

  create(itemLeaseCycle: IItemLeaseCycleRse): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(itemLeaseCycle);
    return this.http
      .post<IItemLeaseCycleRse>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(itemLeaseCycle: IItemLeaseCycleRse): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(itemLeaseCycle);
    return this.http
      .put<IItemLeaseCycleRse>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IItemLeaseCycleRse>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IItemLeaseCycleRse[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(itemLeaseCycle: IItemLeaseCycleRse): IItemLeaseCycleRse {
    const copy: IItemLeaseCycleRse = Object.assign({}, itemLeaseCycle, {
      startTime: itemLeaseCycle.startTime != null && itemLeaseCycle.startTime.isValid() ? itemLeaseCycle.startTime.toJSON() : null,
      endTime: itemLeaseCycle.endTime != null && itemLeaseCycle.endTime.isValid() ? itemLeaseCycle.endTime.toJSON() : null,
      nextBillDay: itemLeaseCycle.nextBillDay != null && itemLeaseCycle.nextBillDay.isValid() ? itemLeaseCycle.nextBillDay.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startTime = res.body.startTime != null ? moment(res.body.startTime) : null;
      res.body.endTime = res.body.endTime != null ? moment(res.body.endTime) : null;
      res.body.nextBillDay = res.body.nextBillDay != null ? moment(res.body.nextBillDay) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((itemLeaseCycle: IItemLeaseCycleRse) => {
        itemLeaseCycle.startTime = itemLeaseCycle.startTime != null ? moment(itemLeaseCycle.startTime) : null;
        itemLeaseCycle.endTime = itemLeaseCycle.endTime != null ? moment(itemLeaseCycle.endTime) : null;
        itemLeaseCycle.nextBillDay = itemLeaseCycle.nextBillDay != null ? moment(itemLeaseCycle.nextBillDay) : null;
      });
    }
    return res;
  }
}
