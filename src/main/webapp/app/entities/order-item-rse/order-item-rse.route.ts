import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrderItemRse } from 'app/shared/model/order-item-rse.model';
import { OrderItemRseService } from './order-item-rse.service';
import { OrderItemRseComponent } from './order-item-rse.component';
import { OrderItemRseDetailComponent } from './order-item-rse-detail.component';
import { OrderItemRseUpdateComponent } from './order-item-rse-update.component';
import { OrderItemRseDeletePopupComponent } from './order-item-rse-delete-dialog.component';
import { IOrderItemRse } from 'app/shared/model/order-item-rse.model';

@Injectable({ providedIn: 'root' })
export class OrderItemRseResolve implements Resolve<IOrderItemRse> {
  constructor(private service: OrderItemRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOrderItemRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OrderItemRse>) => response.ok),
        map((orderItem: HttpResponse<OrderItemRse>) => orderItem.body)
      );
    }
    return of(new OrderItemRse());
  }
}

export const orderItemRoute: Routes = [
  {
    path: '',
    component: OrderItemRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.orderItem.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderItemRseDetailComponent,
    resolve: {
      orderItem: OrderItemRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.orderItem.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderItemRseUpdateComponent,
    resolve: {
      orderItem: OrderItemRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.orderItem.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderItemRseUpdateComponent,
    resolve: {
      orderItem: OrderItemRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.orderItem.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const orderItemPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OrderItemRseDeletePopupComponent,
    resolve: {
      orderItem: OrderItemRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.orderItem.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
