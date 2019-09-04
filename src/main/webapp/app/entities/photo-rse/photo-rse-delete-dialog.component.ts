import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPhotoRse } from 'app/shared/model/photo-rse.model';
import { PhotoRseService } from './photo-rse.service';

@Component({
  selector: 'jhi-photo-rse-delete-dialog',
  templateUrl: './photo-rse-delete-dialog.component.html'
})
export class PhotoRseDeleteDialogComponent {
  photo: IPhotoRse;

  constructor(protected photoService: PhotoRseService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.photoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'photoListModification',
        content: 'Deleted an photo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-photo-rse-delete-popup',
  template: ''
})
export class PhotoRseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ photo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PhotoRseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.photo = photo;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/photo-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/photo-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
