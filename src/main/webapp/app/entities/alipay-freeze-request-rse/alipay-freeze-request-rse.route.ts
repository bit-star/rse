import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AlipayFreezeRequestRse } from 'app/shared/model/alipay-freeze-request-rse.model';
import { AlipayFreezeRequestRseService } from './alipay-freeze-request-rse.service';
import { AlipayFreezeRequestRseComponent } from './alipay-freeze-request-rse.component';
import { AlipayFreezeRequestRseDetailComponent } from './alipay-freeze-request-rse-detail.component';
import { AlipayFreezeRequestRseUpdateComponent } from './alipay-freeze-request-rse-update.component';
import { AlipayFreezeRequestRseDeletePopupComponent } from './alipay-freeze-request-rse-delete-dialog.component';
import { IAlipayFreezeRequestRse } from 'app/shared/model/alipay-freeze-request-rse.model';

@Injectable({ providedIn: 'root' })
export class AlipayFreezeRequestRseResolve implements Resolve<IAlipayFreezeRequestRse> {
  constructor(private service: AlipayFreezeRequestRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAlipayFreezeRequestRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AlipayFreezeRequestRse>) => response.ok),
        map((alipayFreezeRequest: HttpResponse<AlipayFreezeRequestRse>) => alipayFreezeRequest.body)
      );
    }
    return of(new AlipayFreezeRequestRse());
  }
}

export const alipayFreezeRequestRoute: Routes = [
  {
    path: '',
    component: AlipayFreezeRequestRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.alipayFreezeRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AlipayFreezeRequestRseDetailComponent,
    resolve: {
      alipayFreezeRequest: AlipayFreezeRequestRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFreezeRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AlipayFreezeRequestRseUpdateComponent,
    resolve: {
      alipayFreezeRequest: AlipayFreezeRequestRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFreezeRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AlipayFreezeRequestRseUpdateComponent,
    resolve: {
      alipayFreezeRequest: AlipayFreezeRequestRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFreezeRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const alipayFreezeRequestPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AlipayFreezeRequestRseDeletePopupComponent,
    resolve: {
      alipayFreezeRequest: AlipayFreezeRequestRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayFreezeRequest.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
