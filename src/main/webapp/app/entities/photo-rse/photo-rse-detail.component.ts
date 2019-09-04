import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPhotoRse } from 'app/shared/model/photo-rse.model';

@Component({
  selector: 'jhi-photo-rse-detail',
  templateUrl: './photo-rse-detail.component.html'
})
export class PhotoRseDetailComponent implements OnInit {
  photo: IPhotoRse;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ photo }) => {
      this.photo = photo;
    });
  }

  previousState() {
    window.history.back();
  }
}
