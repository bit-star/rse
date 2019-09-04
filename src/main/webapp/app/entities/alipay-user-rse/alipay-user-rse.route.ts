import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AlipayUserRse } from 'app/shared/model/alipay-user-rse.model';
import { AlipayUserRseService } from './alipay-user-rse.service';
import { AlipayUserRseComponent } from './alipay-user-rse.component';
import { AlipayUserRseDetailComponent } from './alipay-user-rse-detail.component';
import { AlipayUserRseUpdateComponent } from './alipay-user-rse-update.component';
import { AlipayUserRseDeletePopupComponent } from './alipay-user-rse-delete-dialog.component';
import { IAlipayUserRse } from 'app/shared/model/alipay-user-rse.model';

@Injectable({ providedIn: 'root' })
export class AlipayUserRseResolve implements Resolve<IAlipayUserRse> {
  constructor(private service: AlipayUserRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAlipayUserRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AlipayUserRse>) => response.ok),
        map((alipayUser: HttpResponse<AlipayUserRse>) => alipayUser.body)
      );
    }
    return of(new AlipayUserRse());
  }
}

export const alipayUserRoute: Routes = [
  {
    path: '',
    component: AlipayUserRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.alipayUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AlipayUserRseDetailComponent,
    resolve: {
      alipayUser: AlipayUserRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AlipayUserRseUpdateComponent,
    resolve: {
      alipayUser: AlipayUserRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AlipayUserRseUpdateComponent,
    resolve: {
      alipayUser: AlipayUserRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const alipayUserPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AlipayUserRseDeletePopupComponent,
    resolve: {
      alipayUser: AlipayUserRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.alipayUser.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
