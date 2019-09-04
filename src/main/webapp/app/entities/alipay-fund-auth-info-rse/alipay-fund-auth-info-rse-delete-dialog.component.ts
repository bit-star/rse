import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAlipayFundAuthInfoRse } from 'app/shared/model/alipay-fund-auth-info-rse.model';
import { AlipayFundAuthInfoRseService } from './alipay-fund-auth-info-rse.service';

@Component({
  selector: 'jhi-alipay-fund-auth-info-rse-delete-dialog',
  templateUrl: './alipay-fund-auth-info-rse-delete-dialog.component.html'
})
export class AlipayFundAuthInfoRseDeleteDialogComponent {
  alipayFundAuthInfo: IAlipayFundAuthInfoRse;

  constructor(
    protected alipayFundAuthInfoService: AlipayFundAuthInfoRseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.alipayFundAuthInfoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'alipayFundAuthInfoListModification',
        content: 'Deleted an alipayFundAuthInfo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-alipay-fund-auth-info-rse-delete-popup',
  template: ''
})
export class AlipayFundAuthInfoRseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alipayFundAuthInfo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AlipayFundAuthInfoRseDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.alipayFundAuthInfo = alipayFundAuthInfo;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/alipay-fund-auth-info-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/alipay-fund-auth-info-rse', { outlets: { popup: null } }]);
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
