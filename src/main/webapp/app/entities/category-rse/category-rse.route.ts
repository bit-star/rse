import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CategoryRse } from 'app/shared/model/category-rse.model';
import { CategoryRseService } from './category-rse.service';
import { CategoryRseComponent } from './category-rse.component';
import { CategoryRseDetailComponent } from './category-rse-detail.component';
import { CategoryRseUpdateComponent } from './category-rse-update.component';
import { CategoryRseDeletePopupComponent } from './category-rse-delete-dialog.component';
import { ICategoryRse } from 'app/shared/model/category-rse.model';

@Injectable({ providedIn: 'root' })
export class CategoryRseResolve implements Resolve<ICategoryRse> {
  constructor(private service: CategoryRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICategoryRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CategoryRse>) => response.ok),
        map((category: HttpResponse<CategoryRse>) => category.body)
      );
    }
    return of(new CategoryRse());
  }
}

export const categoryRoute: Routes = [
  {
    path: '',
    component: CategoryRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.category.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CategoryRseDetailComponent,
    resolve: {
      category: CategoryRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.category.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CategoryRseUpdateComponent,
    resolve: {
      category: CategoryRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.category.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CategoryRseUpdateComponent,
    resolve: {
      category: CategoryRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.category.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const categoryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CategoryRseDeletePopupComponent,
    resolve: {
      category: CategoryRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.category.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
