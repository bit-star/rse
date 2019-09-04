import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAlipayUserRse } from 'app/shared/model/alipay-user-rse.model';
import { AlipayUserRseService } from './alipay-user-rse.service';

@Component({
  selector: 'jhi-alipay-user-rse-delete-dialog',
  templateUrl: './alipay-user-rse-delete-dialog.component.html'
})
export class AlipayUserRseDeleteDialogComponent {
  alipayUser: IAlipayUserRse;

  constructor(
    protected alipayUserService: AlipayUserRseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.alipayUserService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'alipayUserListModification',
        content: 'Deleted an alipayUser'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-alipay-user-rse-delete-popup',
  template: ''
})
export class AlipayUserRseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alipayUser }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AlipayUserRseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.alipayUser = alipayUser;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/alipay-user-rse', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/alipay-user-rse', { outlets: { popup: null } }]);
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
