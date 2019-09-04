import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PhotoRse } from 'app/shared/model/photo-rse.model';
import { PhotoRseService } from './photo-rse.service';
import { PhotoRseComponent } from './photo-rse.component';
import { PhotoRseDetailComponent } from './photo-rse-detail.component';
import { PhotoRseUpdateComponent } from './photo-rse-update.component';
import { PhotoRseDeletePopupComponent } from './photo-rse-delete-dialog.component';
import { IPhotoRse } from 'app/shared/model/photo-rse.model';

@Injectable({ providedIn: 'root' })
export class PhotoRseResolve implements Resolve<IPhotoRse> {
  constructor(private service: PhotoRseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPhotoRse> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PhotoRse>) => response.ok),
        map((photo: HttpResponse<PhotoRse>) => photo.body)
      );
    }
    return of(new PhotoRse());
  }
}

export const photoRoute: Routes = [
  {
    path: '',
    component: PhotoRseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rseApp.photo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PhotoRseDetailComponent,
    resolve: {
      photo: PhotoRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.photo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PhotoRseUpdateComponent,
    resolve: {
      photo: PhotoRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.photo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PhotoRseUpdateComponent,
    resolve: {
      photo: PhotoRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.photo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const photoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PhotoRseDeletePopupComponent,
    resolve: {
      photo: PhotoRseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rseApp.photo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
