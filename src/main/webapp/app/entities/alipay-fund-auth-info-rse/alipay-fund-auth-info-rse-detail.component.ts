import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlipayFundAuthInfoRse } from 'app/shared/model/alipay-fund-auth-info-rse.model';

@Component({
  selector: 'jhi-alipay-fund-auth-info-rse-detail',
  templateUrl: './alipay-fund-auth-info-rse-detail.component.html'
})
export class AlipayFundAuthInfoRseDetailComponent implements OnInit {
  alipayFundAuthInfo: IAlipayFundAuthInfoRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alipayFundAuthInfo }) => {
      this.alipayFundAuthInfo = alipayFundAuthInfo;
    });
  }

  previousState() {
    window.history.back();
  }
}
