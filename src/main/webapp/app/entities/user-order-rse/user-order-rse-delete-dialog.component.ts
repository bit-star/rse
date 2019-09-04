import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';
import { UserOrderRseService } from './user-order-rse.service';

@Component({
  selector: 'jhi-user-order-rse-delete-dialog',
  templateUrl: './user-order-rse-delete-dialog.component.html'
})
export class UserOrderRseDeleteDialogComponent {
  userOrder: IUserOrderRse;

  constructor(
    protected userOrderService: UserOrderRseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.userOrderService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'userOrderListModification',
        content: 'Deleted an userOrder'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-user-order-rse-delete-popup',
  template: ''
})
export class UserOrderRseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ userOrder }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(UserOrderRseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.userOrder = userOrder;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/user-order-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/user-order-rse', { outlets: { popup: null } }]);
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
