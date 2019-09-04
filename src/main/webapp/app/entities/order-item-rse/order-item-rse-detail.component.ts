import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderItemRse } from 'app/shared/model/order-item-rse.model';

@Component({
  selector: 'jhi-order-item-rse-detail',
  templateUrl: './order-item-rse-detail.component.html'
})
export class OrderItemRseDetailComponent implements OnInit {
  orderItem: IOrderItemRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ orderItem }) => {
      this.orderItem = orderItem;
    });
  }

  previousState() {
    window.history.back();
  }
}
