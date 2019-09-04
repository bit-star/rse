import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITagRse } from 'app/shared/model/tag-rse.model';
import { TagRseService } from './tag-rse.service';

@Component({
  selector: 'jhi-tag-rse-delete-dialog',
  templateUrl: './tag-rse-delete-dialog.component.html'
})
export class TagRseDeleteDialogComponent {
  tag: ITagRse;

  constructor(protected tagService: TagRseService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.tagService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'tagListModification',
        content: 'Deleted an tag'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tag-rse-delete-popup',
  template: ''
})
export class TagRseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tag }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TagRseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.tag = tag;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/tag-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/tag-rse', { outlets: { popup: null } }]);
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
