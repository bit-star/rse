import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TagRse } from 'app/shared/model/tag-rse.model';
import { TagRseService } from './tag-rse.service';
import { TagRseComponent } from './tag-rse.component';
import { TagRseDetailComponent } from './tag-rse-detail.component';
import { TagRseUpdateComponent } from './tag-rse-update.component';
import { TagRseDeletePopupComponent } from './tag-rse-delete-dialog.component';
import { ITagRse } from 'app/shared/model/tag-rse.model';

@Injectable({ providedIn: 'root' })
export class TagRseResolve implements Resolve<ITagRse> {
  constructor(private service: TagRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITagRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TagRse>) => response.ok),
        map((tag: HttpResponse<TagRse>) => tag.body)
      );
    }
    return of(new TagRse());
  }
}

export const tagRoute: Routes = [
  {
    path: '',
    component: TagRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.tag.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TagRseDetailComponent,
    resolve: {
      tag: TagRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.tag.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TagRseUpdateComponent,
    resolve: {
      tag: TagRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.tag.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TagRseUpdateComponent,
    resolve: {
      tag: TagRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.tag.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tagPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TagRseDeletePopupComponent,
    resolve: {
      tag: TagRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.tag.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
