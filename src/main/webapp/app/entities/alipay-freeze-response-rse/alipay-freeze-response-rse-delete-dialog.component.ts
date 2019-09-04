import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAlipayFreezeResponseRse } from 'app/shared/model/alipay-freeze-response-rse.model';
import { AlipayFreezeResponseRseService } from './alipay-freeze-response-rse.service';

@Component({
  selector: 'jhi-alipay-freeze-response-rse-delete-dialog',
  templateUrl: './alipay-freeze-response-rse-delete-dialog.component.html'
})
export class AlipayFreezeResponseRseDeleteDialogComponent {
  alipayFreezeResponse: IAlipayFreezeResponseRse;

  constructor(
    protected alipayFreezeResponseService: AlipayFreezeResponseRseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.alipayFreezeResponseService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'alipayFreezeResponseListModification',
        content: 'Deleted an alipayFreezeResponse'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-alipay-freeze-response-rse-delete-popup',
  template: ''
})
export class AlipayFreezeResponseRseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alipayFreezeResponse }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AlipayFreezeResponseRseDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.alipayFreezeResponse = alipayFreezeResponse;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/alipay-freeze-response-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/alipay-freeze-response-rse', { outlets: { popup: null } }]);
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
