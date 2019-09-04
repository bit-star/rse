import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ShippingAddressRse } from 'app/shared/model/shipping-address-rse.model';
import { ShippingAddressRseService } from './shipping-address-rse.service';
import { ShippingAddressRseComponent } from './shipping-address-rse.component';
import { ShippingAddressRseDetailComponent } from './shipping-address-rse-detail.component';
import { ShippingAddressRseUpdateComponent } from './shipping-address-rse-update.component';
import { ShippingAddressRseDeletePopupComponent } from './shipping-address-rse-delete-dialog.component';
import { IShippingAddressRse } from 'app/shared/model/shipping-address-rse.model';

@Injectable({ providedIn: 'root' })
export class ShippingAddressRseResolve implements Resolve<IShippingAddressRse> {
  constructor(private service: ShippingAddressRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IShippingAddressRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ShippingAddressRse>) => response.ok),
        map((shippingAddress: HttpResponse<ShippingAddressRse>) => shippingAddress.body)
      );
    }
    return of(new ShippingAddressRse());
  }
}

export const shippingAddressRoute: Routes = [
  {
    path: '',
    component: ShippingAddressRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.shippingAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ShippingAddressRseDetailComponent,
    resolve: {
      shippingAddress: ShippingAddressRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.shippingAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ShippingAddressRseUpdateComponent,
    resolve: {
      shippingAddress: ShippingAddressRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.shippingAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ShippingAddressRseUpdateComponent,
    resolve: {
      shippingAddress: ShippingAddressRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.shippingAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const shippingAddressPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ShippingAddressRseDeletePopupComponent,
    resolve: {
      shippingAddress: ShippingAddressRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.shippingAddress.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
