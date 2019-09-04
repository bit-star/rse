import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAlipayFreezeRequestRse } from 'app/shared/model/alipay-freeze-request-rse.model';
import { AlipayFreezeRequestRseService } from './alipay-freeze-request-rse.service';

@Component({
  selector: 'jhi-alipay-freeze-request-rse-delete-dialog',
  templateUrl: './alipay-freeze-request-rse-delete-dialog.component.html'
})
export class AlipayFreezeRequestRseDeleteDialogComponent {
  alipayFreezeRequest: IAlipayFreezeRequestRse;

  constructor(
    protected alipayFreezeRequestService: AlipayFreezeRequestRseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.alipayFreezeRequestService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'alipayFreezeRequestListModification',
        content: 'Deleted an alipayFreezeRequest'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-alipay-freeze-request-rse-delete-popup',
  template: ''
})
export class AlipayFreezeRequestRseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alipayFreezeRequest }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AlipayFreezeRequestRseDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.alipayFreezeRequest = alipayFreezeRequest;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/alipay-freeze-request-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/alipay-freeze-request-rse', { outlets: { popup: null } }]);
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
