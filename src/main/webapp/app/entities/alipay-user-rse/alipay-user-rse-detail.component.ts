import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlipayUserRse } from 'app/shared/model/alipay-user-rse.model';

@Component({
  selector: 'jhi-alipay-user-rse-detail',
  templateUrl: './alipay-user-rse-detail.component.html'
})
export class AlipayUserRseDetailComponent implements OnInit {
  alipayUser: IAlipayUserRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ alipayUser }) => {
      this.alipayUser = alipayUser;
    });
  }

  previousState() {
    window.history.back();
  }
}
