import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItemLeaseCycleRse } from 'app/shared/model/item-lease-cycle-rse.model';
import { ItemLeaseCycleRseService } from './item-lease-cycle-rse.service';

@Component({
  selector: 'jhi-item-lease-cycle-rse-delete-dialog',
  templateUrl: './item-lease-cycle-rse-delete-dialog.component.html'
})
export class ItemLeaseCycleRseDeleteDialogComponent {
  itemLeaseCycle: IItemLeaseCycleRse;

  constructor(
    protected itemLeaseCycleService: ItemLeaseCycleRseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.itemLeaseCycleService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'itemLeaseCycleListModification',
        content: 'Deleted an itemLeaseCycle'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-item-lease-cycle-rse-delete-popup',
  template: ''
})
export class ItemLeaseCycleRseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ itemLeaseCycle }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ItemLeaseCycleRseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.itemLeaseCycle = itemLeaseCycle;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/item-lease-cycle-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/item-lease-cycle-rse', { outlets: { popup: null } }]);
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
