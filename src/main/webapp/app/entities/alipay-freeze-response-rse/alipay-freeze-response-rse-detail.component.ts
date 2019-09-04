import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlipayFreezeResponseRse } from 'app/shared/model/alipay-freeze-response-rse.model';

@Component({
  selector: 'jhi-alipay-freeze-response-rse-detail',
  templateUrl: './alipay-freeze-response-rse-detail.component.html'
})
export class AlipayFreezeResponseRseDetailComponent implements OnInit {
  alipayFreezeResponse: IAlipayFreezeResponseRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alipayFreezeResponse }) => {
      this.alipayFreezeResponse = alipayFreezeResponse;
    });
  }

  previousState() {
    window.history.back();
  }
}
