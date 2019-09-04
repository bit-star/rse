import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISpecificationRse } from 'app/shared/model/specification-rse.model';
import { SpecificationRseService } from './specification-rse.service';

@Component({
  selector: 'jhi-specification-rse-delete-dialog',
  templateUrl: './specification-rse-delete-dialog.component.html'
})
export class SpecificationRseDeleteDialogComponent {
  specification: ISpecificationRse;

  constructor(
    protected specificationService: SpecificationRseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.specificationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'specificationListModification',
        content: 'Deleted an specification'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-specification-rse-delete-popup',
  template: ''
})
export class SpecificationRseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ specification }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SpecificationRseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.specification = specification;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/specification-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/specification-rse', { outlets: { popup: null } }]);
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
