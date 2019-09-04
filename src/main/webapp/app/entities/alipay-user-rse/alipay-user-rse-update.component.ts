import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IAlipayUserRse, AlipayUserRse } from 'app/shared/model/alipay-user-rse.model';
import { AlipayUserRseService } from './alipay-user-rse.service';

@Component({
  selector: 'jhi-alipay-user-rse-update',
  templateUrl: './alipay-user-rse-update.component.html'
})
export class AlipayUserRseUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    userId: [],
    avatar: [],
    nickName: [],
    mobile: [],
    gender: [],
    countryCode: [],
    province: [],
    city: [],
    accessToken: [],
    authTokenType: [],
    expiresIn: [],
    alipayUserId: [],
    reExpiresIn: [],
    refreshToken: []
  });

  constructor(protected alipayUserService: AlipayUserRseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ alipayUser }) => {
      this.updateForm(alipayUser);
    });
  }

  updateForm(alipayUser: IAlipayUserRse) {
    this.editForm.patchValue({
      id: alipayUser.id,
      userId: alipayUser.userId,
      avatar: alipayUser.avatar,
      nickName: alipayUser.nickName,
      mobile: alipayUser.mobile,
      gender: alipayUser.gender,
      countryCode: alipayUser.countryCode,
      province: alipayUser.province,
      city: alipayUser.city,
      accessToken: alipayUser.accessToken,
      authTokenType: alipayUser.authTokenType,
      expiresIn: alipayUser.expiresIn,
      alipayUserId: alipayUser.alipayUserId,
      reExpiresIn: alipayUser.reExpiresIn,
      refreshToken: alipayUser.refreshToken
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const alipayUser = this.createFromForm();
    if (alipayUser.id !== undefined) {
      this.subscribeToSaveResponse(this.alipayUserService.update(alipayUser));
    } else {
      this.subscribeToSaveResponse(this.alipayUserService.create(alipayUser));
    }
  }

  private createFromForm(): IAlipayUserRse {
    return {
      ...new AlipayUserRse(),
      id: this.editForm.get(['id']).value,
      userId: this.editForm.get(['userId']).value,
      avatar: this.editForm.get(['avatar']).value,
      nickName: this.editForm.get(['nickName']).value,
      mobile: this.editForm.get(['mobile']).value,
      gender: this.editForm.get(['gender']).value,
      countryCode: this.editForm.get(['countryCode']).value,
      province: this.editForm.get(['province']).value,
      city: this.editForm.get(['city']).value,
      accessToken: this.editForm.get(['accessToken']).value,
      authTokenType: this.editForm.get(['authTokenType']).value,
      expiresIn: this.editForm.get(['expiresIn']).value,
      alipayUserId: this.editForm.get(['alipayUserId']).value,
      reExpiresIn: this.editForm.get(['reExpiresIn']).value,
      refreshToken: this.editForm.get(['refreshToken']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlipayUserRse>>) {
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
