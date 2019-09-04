import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ICategoryRse, CategoryRse } from 'app/shared/model/category-rse.model';
import { CategoryRseService } from './category-rse.service';

@Component({
  selector: 'jhi-category-rse-update',
  templateUrl: './category-rse-update.component.html'
})
export class CategoryRseUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    order: []
  });

  constructor(protected categoryService: CategoryRseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ category }) => {
      this.updateForm(category);
    });
  }

  updateForm(category: ICategoryRse) {
    this.editForm.patchValue({
      id: category.id,
      name: category.name,
      order: category.order
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const category = this.createFromForm();
    if (category.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryService.update(category));
    } else {
      this.subscribeToSaveResponse(this.categoryService.create(category));
    }
  }

  private createFromForm(): ICategoryRse {
    return {
      ...new CategoryRse(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      order: this.editForm.get(['order']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategoryRse>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
