import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItemLeaseCycleRse } from 'app/shared/model/item-lease-cycle-rse.model';

@Component({
  selector: 'jhi-item-lease-cycle-rse-detail',
  templateUrl: './item-lease-cycle-rse-detail.component.html'
})
export class ItemLeaseCycleRseDetailComponent implements OnInit {
  itemLeaseCycle: IItemLeaseCycleRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ itemLeaseCycle }) => {
      this.itemLeaseCycle = itemLeaseCycle;
    });
  }

  previousState() {
    window.history.back();
  }
}
