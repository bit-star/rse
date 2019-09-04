import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserOrderRse } from 'app/shared/model/user-order-rse.model';

@Component({
  selector: 'jhi-user-order-rse-detail',
  templateUrl: './user-order-rse-detail.component.html'
})
export class UserOrderRseDetailComponent implements OnInit {
  userOrder: IUserOrderRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ userOrder }) => {
      this.userOrder = userOrder;
    });
  }

  previousState() {
    window.history.back();
  }
}
