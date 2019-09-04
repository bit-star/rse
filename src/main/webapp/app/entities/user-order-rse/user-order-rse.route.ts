import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UserOrderRse } from 'app/shared/model/user-order-rse.model';
import { UserOrderRseService } from './user-order-rse.service';
import { UserOrderRseComponent } from './user-order-rse.component';
import { UserOrderRseDetailComponent } from './user-order-rse-detail.component';
import { UserOrderRseUpdateComponent } from './user-order-rse-update.component';
import { UserOrderRseDeletePopupComponent } from './user-order-rse-delete-dialog.component';
import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';

@Injectable({ providedIn: 'root' })
export class UserOrderRseResolve implements Resolve<IUserOrderRse> {
  constructor(private service: UserOrderRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IUserOrderRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<UserOrderRse>) => response.ok),
        map((userOrder: HttpResponse<UserOrderRse>) => userOrder.body)
      );
    }
    return of(new UserOrderRse());
  }
}

export const userOrderRoute: Routes = [
  {
    path: '',
    component: UserOrderRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.userOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UserOrderRseDetailComponent,
    resolve: {
      userOrder: UserOrderRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.userOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UserOrderRseUpdateComponent,
    resolve: {
      userOrder: UserOrderRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.userOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UserOrderRseUpdateComponent,
    resolve: {
      userOrder: UserOrderRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.userOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const userOrderPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: UserOrderRseDeletePopupComponent,
    resolve: {
      userOrder: UserOrderRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.userOrder.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
