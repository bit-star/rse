import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ITagRse, TagRse } from 'app/shared/model/tag-rse.model';
import { TagRseService } from './tag-rse.service';
import { ICommodityRse } from 'app/shared/model/commodity-rse.model';
import { CommodityRseService } from 'app/entities/commodity-rse';

@Component({
  selector: 'jhi-tag-rse-update',
  templateUrl: './tag-rse-update.component.html'
})
export class TagRseUpdateComponent implements OnInit {
  isSaving: boolean;

  commodities: ICommodityRse[];

  editForm = this.fb.group({
    id: [],
    name: [],
    icon: [],
    remark: [],
    commodity: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected tagService: TagRseService,
    protected commodityService: CommodityRseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tag }) => {
      this.updateForm(tag);
    });
    this.commodityService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICommodityRse[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICommodityRse[]>) => response.body)
      )
      .subscribe((res: ICommodityRse[]) => (this.commodities = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(tag: ITagRse) {
    this.editForm.patchValue({
      id: tag.id,
      name: tag.name,
      icon: tag.icon,
      remark: tag.remark,
      commodity: tag.commodity
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tag = this.createFromForm();
    if (tag.id !== undefined) {
      this.subscribeToSaveResponse(this.tagService.update(tag));
    } else {
      this.subscribeToSaveResponse(this.tagService.create(tag));
    }
  }

  private createFromForm(): ITagRse {
    return {
      ...new TagRse(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      icon: this.editForm.get(['icon']).value,
      remark: this.editForm.get(['remark']).value,
      commodity: this.editForm.get(['commodity']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITagRse>>) {
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
