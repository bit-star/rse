import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ItemLeaseCycleRse } from 'app/shared/model/item-lease-cycle-rse.model';
import { ItemLeaseCycleRseService } from './item-lease-cycle-rse.service';
import { ItemLeaseCycleRseComponent } from './item-lease-cycle-rse.component';
import { ItemLeaseCycleRseDetailComponent } from './item-lease-cycle-rse-detail.component';
import { ItemLeaseCycleRseUpdateComponent } from './item-lease-cycle-rse-update.component';
import { ItemLeaseCycleRseDeletePopupComponent } from './item-lease-cycle-rse-delete-dialog.component';
import { IItemLeaseCycleRse } from 'app/shared/model/item-lease-cycle-rse.model';

@Injectable({ providedIn: 'root' })
export class ItemLeaseCycleRseResolve implements Resolve<IItemLeaseCycleRse> {
  constructor(private service: ItemLeaseCycleRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IItemLeaseCycleRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ItemLeaseCycleRse>) => response.ok),
        map((itemLeaseCycle: HttpResponse<ItemLeaseCycleRse>) => itemLeaseCycle.body)
      );
    }
    return of(new ItemLeaseCycleRse());
  }
}

export const itemLeaseCycleRoute: Routes = [
  {
    path: '',
    component: ItemLeaseCycleRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.itemLeaseCycle.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ItemLeaseCycleRseDetailComponent,
    resolve: {
      itemLeaseCycle: ItemLeaseCycleRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.itemLeaseCycle.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ItemLeaseCycleRseUpdateComponent,
    resolve: {
      itemLeaseCycle: ItemLeaseCycleRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.itemLeaseCycle.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ItemLeaseCycleRseUpdateComponent,
    resolve: {
      itemLeaseCycle: ItemLeaseCycleRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.itemLeaseCycle.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const itemLeaseCyclePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ItemLeaseCycleRseDeletePopupComponent,
    resolve: {
      itemLeaseCycle: ItemLeaseCycleRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.itemLeaseCycle.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
