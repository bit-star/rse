import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShippingAddressRse } from 'app/shared/model/shipping-address-rse.model';

@Component({
  selector: 'jhi-shipping-address-rse-detail',
  templateUrl: './shipping-address-rse-detail.component.html'
})
export class ShippingAddressRseDetailComponent implements OnInit {
  shippingAddress: IShippingAddressRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ shippingAddress }) => {
      this.shippingAddress = shippingAddress;
    });
  }

  previousState() {
    window.history.back();
  }
}
