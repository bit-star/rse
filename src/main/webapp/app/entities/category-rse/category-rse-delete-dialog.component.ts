import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategoryRse } from 'app/shared/model/category-rse.model';
import { CategoryRseService } from './category-rse.service';

@Component({
  selector: 'jhi-category-rse-delete-dialog',
  templateUrl: './category-rse-delete-dialog.component.html'
})
export class CategoryRseDeleteDialogComponent {
  category: ICategoryRse;

  constructor(protected categoryService: CategoryRseService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.categoryService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'categoryListModification',
        content: 'Deleted an category'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-category-rse-delete-popup',
  template: ''
})
export class CategoryRseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ category }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CategoryRseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.category = category;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/category-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/category-rse', { outlets: { popup: null } }]);
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
