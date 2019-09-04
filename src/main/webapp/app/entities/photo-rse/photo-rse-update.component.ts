import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPhotoRse, PhotoRse } from 'app/shared/model/photo-rse.model';
import { PhotoRseService } from './photo-rse.service';
import { ICommodityRse } from 'app/shared/model/commodity-rse.model';
import { CommodityRseService } from 'app/entities/commodity-rse';

@Component({
  selector: 'jhi-photo-rse-update',
  templateUrl: './photo-rse-update.component.html'
})
export class PhotoRseUpdateComponent implements OnInit {
  isSaving: boolean;

  commodities: ICommodityRse[];

  editForm = this.fb.group({
    id: [],
    originalImage: [],
    thumbnail: [],
    width: [],
    height: [],
    rankOrder: [],
    commodity: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected photoService: PhotoRseService,
    protected commodityService: CommodityRseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ photo }) => {
      this.updateForm(photo);
    });
    this.commodityService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICommodityRse[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICommodityRse[]>) => response.body)
      )
      .subscribe((res: ICommodityRse[]) => (this.commodities = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(photo: IPhotoRse) {
    this.editForm.patchValue({
      id: photo.id,
      originalImage: photo.originalImage,
      thumbnail: photo.thumbnail,
      width: photo.width,
      height: photo.height,
      rankOrder: photo.rankOrder,
      commodity: photo.commodity
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const photo = this.createFromForm();
    if (photo.id !== undefined) {
      this.subscribeToSaveResponse(this.photoService.update(photo));
    } else {
      this.subscribeToSaveResponse(this.photoService.create(photo));
    }
  }

  private createFromForm(): IPhotoRse {
    return {
      ...new PhotoRse(),
      id: this.editForm.get(['id']).value,
      originalImage: this.editForm.get(['originalImage']).value,
      thumbnail: this.editForm.get(['thumbnail']).value,
      width: this.editForm.get(['width']).value,
      height: this.editForm.get(['height']).value,
      rankOrder: this.editForm.get(['rankOrder']).value,
      commodity: this.editForm.get(['commodity']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPhotoRse>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackCommodityById(index: number, item: ICommodityRse) {
    return item.id;
  }
}
