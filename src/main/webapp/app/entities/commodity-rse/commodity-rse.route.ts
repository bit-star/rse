import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CommodityRse } from 'app/shared/model/commodity-rse.model';
import { CommodityRseService } from './commodity-rse.service';
import { CommodityRseComponent } from './commodity-rse.component';
import { CommodityRseDetailComponent } from './commodity-rse-detail.component';
import { CommodityRseUpdateComponent } from './commodity-rse-update.component';
import { CommodityRseDeletePopupComponent } from './commodity-rse-delete-dialog.component';
import { ICommodityRse } from 'app/shared/model/commodity-rse.model';

@Injectable({ providedIn: 'root' })
export class CommodityRseResolve implements Resolve<ICommodityRse> {
  constructor(private service: CommodityRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICommodityRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CommodityRse>) => response.ok),
        map((commodity: HttpResponse<CommodityRse>) => commodity.body)
      );
    }
    return of(new CommodityRse());
  }
}

export const commodityRoute: Routes = [
  {
    path: '',
    component: CommodityRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.commodity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CommodityRseDetailComponent,
    resolve: {
      commodity: CommodityRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.commodity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CommodityRseUpdateComponent,
    resolve: {
      commodity: CommodityRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.commodity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CommodityRseUpdateComponent,
    resolve: {
      commodity: CommodityRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.commodity.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const commodityPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CommodityRseDeletePopupComponent,
    resolve: {
      commodity: CommodityRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.commodity.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
