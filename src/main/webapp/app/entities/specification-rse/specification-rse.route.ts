import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SpecificationRse } from 'app/shared/model/specification-rse.model';
import { SpecificationRseService } from './specification-rse.service';
import { SpecificationRseComponent } from './specification-rse.component';
import { SpecificationRseDetailComponent } from './specification-rse-detail.component';
import { SpecificationRseUpdateComponent } from './specification-rse-update.component';
import { SpecificationRseDeletePopupComponent } from './specification-rse-delete-dialog.component';
import { ISpecificationRse } from 'app/shared/model/specification-rse.model';

@Injectable({ providedIn: 'root' })
export class SpecificationRseResolve implements Resolve<ISpecificationRse> {
  constructor(private service: SpecificationRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISpecificationRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SpecificationRse>) => response.ok),
        map((specification: HttpResponse<SpecificationRse>) => specification.body)
      );
    }
    return of(new SpecificationRse());
  }
}

export const specificationRoute: Routes = [
  {
    path: '',
    component: SpecificationRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.specification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SpecificationRseDetailComponent,
    resolve: {
      specification: SpecificationRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.specification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SpecificationRseUpdateComponent,
    resolve: {
      specification: SpecificationRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.specification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SpecificationRseUpdateComponent,
    resolve: {
      specification: SpecificationRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.specification.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const specificationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SpecificationRseDeletePopupComponent,
    resolve: {
      specification: SpecificationRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.specification.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
