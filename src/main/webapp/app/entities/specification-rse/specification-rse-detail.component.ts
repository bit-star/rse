import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISpecificationRse } from 'app/shared/model/specification-rse.model';

@Component({
  selector: 'jhi-specification-rse-detail',
  templateUrl: './specification-rse-detail.component.html'
})
export class SpecificationRseDetailComponent implements OnInit {
  specification: ISpecificationRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ specification }) => {
      this.specification = specification;
    });
  }

  previousState() {
    window.history.back();
  }
}
