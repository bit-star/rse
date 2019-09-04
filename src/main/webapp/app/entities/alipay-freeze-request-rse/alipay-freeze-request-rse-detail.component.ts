import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlipayFreezeRequestRse } from 'app/shared/model/alipay-freeze-request-rse.model';

@Component({
  selector: 'jhi-alipay-freeze-request-rse-detail',
  templateUrl: './alipay-freeze-request-rse-detail.component.html'
})
export class AlipayFreezeRequestRseDetailComponent implements OnInit {
  alipayFreezeRequest: IAlipayFreezeRequestRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alipayFreezeRequest }) => {
      this.alipayFreezeRequest = alipayFreezeRequest;
    });
  }

  previousState() {
    window.history.back();
  }
}
