import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AlipayFreezeResponseRse } from 'app/shared/model/alipay-freeze-response-rse.model';
import { AlipayFreezeResponseRseService } from './alipay-freeze-response-rse.service';
import { AlipayFreezeResponseRseComponent } from './alipay-freeze-response-rse.component';
import { AlipayFreezeResponseRseDetailComponent } from './alipay-freeze-response-rse-detail.component';
import { AlipayFreezeResponseRseUpdateComponent } from './alipay-freeze-response-rse-update.component';
import { AlipayFreezeResponseRseDeletePopupComponent } from './alipay-freeze-response-rse-delete-dialog.component';
import { IAlipayFreezeResponseRse } from 'app/shared/model/alipay-freeze-response-rse.model';

@Injectable({ providedIn: 'root' })
export class AlipayFreezeResponseRseResolve implements Resolve<IAlipayFreezeResponseRse> {
  constructor(private service: AlipayFreezeResponseRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAlipayFreezeResponseRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AlipayFreezeResponseRse>) => response.ok),
        map((alipayFreezeResponse: HttpResponse<AlipayFreezeResponseRse>) => alipayFreezeResponse.body)
      );
    }
    return of(new AlipayFreezeResponseRse());
  }
}

export const alipayFreezeResponseRoute: Routes = [
  {
    path: '',
    component: AlipayFreezeResponseRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.alipayFreezeResponse.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AlipayFreezeResponseRseDetailComponent,
    resolve: {
      alipayFreezeResponse: AlipayFreezeResponseRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFreezeResponse.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AlipayFreezeResponseRseUpdateComponent,
    resolve: {
      alipayFreezeResponse: AlipayFreezeResponseRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFreezeResponse.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AlipayFreezeResponseRseUpdateComponent,
    resolve: {
      alipayFreezeResponse: AlipayFreezeResponseRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFreezeResponse.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const alipayFreezeResponsePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AlipayFreezeResponseRseDeletePopupComponent,
    resolve: {
      alipayFreezeResponse: AlipayFreezeResponseRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFreezeResponse.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
