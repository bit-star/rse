import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITagRse } from 'app/shared/model/tag-rse.model';

@Component({
  selector: 'jhi-tag-rse-detail',
  templateUrl: './tag-rse-detail.component.html'
})
export class TagRseDetailComponent implements OnInit {
  tag: ITagRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tag }) => {
      this.tag = tag;
    });
  }

  previousState() {
    window.history.back();
  }
}
