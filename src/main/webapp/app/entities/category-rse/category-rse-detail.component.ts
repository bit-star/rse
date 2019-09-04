import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategoryRse } from 'app/shared/model/category-rse.model';

@Component({
  selector: 'jhi-category-rse-detail',
  templateUrl: './category-rse-detail.component.html'
})
export class CategoryRseDetailComponent implements OnInit {
  category: ICategoryRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ category }) => {
      this.category = category;
    });
  }

  previousState() {
    window.history.back();
  }
}
