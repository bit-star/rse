import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISpecificationRse, SpecificationRse } from 'app/shared/model/specification-rse.model';
import { SpecificationRseService } from './specification-rse.service';
import { ICommodityRse } from 'app/shared/model/commodity-rse.model';
import { CommodityRseService } from 'app/entities/commodity-rse';

@Component({
  selector: 'jhi-specification-rse-update',
  templateUrl: './specification-rse-update.component.html'
})
export class SpecificationRseUpdateComponent implements OnInit {
  isSaving: boolean;

  commodities: ICommodityRse[];

  editForm = this.fb.group({
    id: [],
    name: [],
    value: [],
    commodity: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected specificationService: SpecificationRseService,
    protected commodityService: CommodityRseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ specification }) => {
      this.updateForm(specification);
    });
    this.commodityService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICommodityRse[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICommodityRse[]>) => response.body)
      )
      .subscribe((res: ICommodityRse[]) => (this.commodities = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(specification: ISpecificationRse) {
    this.editForm.patchValue({
      id: specification.id,
      name: specification.name,
      value: specification.value,
      commodity: specification.commodity
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const specification = this.createFromForm();
    if (specification.id !== undefined) {
      this.subscribeToSaveResponse(this.specificationService.update(specification));
    } else {
      this.subscribeToSaveResponse(this.specificationService.create(specification));
    }
  }

  private createFromForm(): ISpecificationRse {
    return {
      ...new SpecificationRse(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      value: this.editForm.get(['value']).value,
      commodity: this.editForm.get(['commodity']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpecificationRse>>) {
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
