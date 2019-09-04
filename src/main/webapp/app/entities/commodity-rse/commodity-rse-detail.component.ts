import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommodityRse } from 'app/shared/model/commodity-rse.model';

@Component({
  selector: 'jhi-commodity-rse-detail',
  templateUrl: './commodity-rse-detail.component.html'
})
export class CommodityRseDetailComponent implements OnInit {
  commodity: ICommodityRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ commodity }) => {
      this.commodity = commodity;
    });
  }

  previousState() {
    window.history.back();
  }
}
