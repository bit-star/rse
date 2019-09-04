import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AlipayFundAuthInfoRse } from 'app/shared/model/alipay-fund-auth-info-rse.model';
import { AlipayFundAuthInfoRseService } from './alipay-fund-auth-info-rse.service';
import { AlipayFundAuthInfoRseComponent } from './alipay-fund-auth-info-rse.component';
import { AlipayFundAuthInfoRseDetailComponent } from './alipay-fund-auth-info-rse-detail.component';
import { AlipayFundAuthInfoRseUpdateComponent } from './alipay-fund-auth-info-rse-update.component';
import { AlipayFundAuthInfoRseDeletePopupComponent } from './alipay-fund-auth-info-rse-delete-dialog.component';
import { IAlipayFundAuthInfoRse } from 'app/shared/model/alipay-fund-auth-info-rse.model';

@Injectable({ providedIn: 'root' })
export class AlipayFundAuthInfoRseResolve implements Resolve<IAlipayFundAuthInfoRse> {
  constructor(private service: AlipayFundAuthInfoRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAlipayFundAuthInfoRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AlipayFundAuthInfoRse>) => response.ok),
        map((alipayFundAuthInfo: HttpResponse<AlipayFundAuthInfoRse>) => alipayFundAuthInfo.body)
      );
    }
    return of(new AlipayFundAuthInfoRse());
  }
}

export const alipayFundAuthInfoRoute: Routes = [
  {
    path: '',
    component: AlipayFundAuthInfoRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.alipayFundAuthInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AlipayFundAuthInfoRseDetailComponent,
    resolve: {
      alipayFundAuthInfo: AlipayFundAuthInfoRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFundAuthInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AlipayFundAuthInfoRseUpdateComponent,
    resolve: {
      alipayFundAuthInfo: AlipayFundAuthInfoRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFundAuthInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AlipayFundAuthInfoRseUpdateComponent,
    resolve: {
      alipayFundAuthInfo: AlipayFundAuthInfoRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFundAuthInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const alipayFundAuthInfoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AlipayFundAuthInfoRseDeletePopupComponent,
    resolve: {
      alipayFundAuthInfo: AlipayFundAuthInfoRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFundAuthInfo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
