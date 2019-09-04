/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AlipayFundAuthInfoRseService } from 'app/entities/alipay-fund-auth-info-rse/alipay-fund-auth-info-rse.service';
import { IAlipayFundAuthInfoRse, AlipayFundAuthInfoRse } from 'app/shared/model/alipay-fund-auth-info-rse.model';

describe('Service Tests', () => {
  describe('AlipayFundAuthInfoRse Service', () => {
    let injector: TestBed;
    let service: AlipayFundAuthInfoRseService;
    let httpMock: HttpTestingController;
    let elemDefault: IAlipayFundAuthInfoRse;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(AlipayFundAuthInfoRseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AlipayFundAuthInfoRse(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a AlipayFundAuthInfoRse', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new AlipayFundAuthInfoRse(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a AlipayFundAuthInfoRse', async () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            msg: 'BBBBBB',
            subCode: 'BBBBBB',
            subMsg: 'BBBBBB',
            body: 'BBBBBB',
            paramsStr: 'BBBBBB',
            amount: 'BBBBBB',
            authNo: 'BBBBBB',
            creditAmount: 'BBBBBB',
            extraParam: 'BBBBBB',
            fundAmount: 'BBBBBB',
            gmtCreateStr: 'BBBBBB',
            gmtTransStr: 'BBBBBB',
            operationId: 'BBBBBB',
            operationType: 'BBBBBB',
            orderTitle: 'BBBBBB',
            outOrderNo: 'BBBBBB',
            outRequestNo: 'BBBBBB',
            payerLogonId: 'BBBBBB',
            payerUserId: 'BBBBBB',
            preAuthType: 'BBBBBB',
            remark: 'BBBBBB',
            restAmount: 'BBBBBB',
            restCreditAmount: 'BBBBBB',
            restFundAmount: 'BBBBBB',
            status: 'BBBBBB',
            totalFreezeAmount: 'BBBBBB',
            totalFreezeCreditAmount: 'BBBBBB',
            totalFreezeFundAmount: 'BBBBBB',
            totalPayAmount: 'BBBBBB',
            totalPayCreditAmount: 'BBBBBB',
            totalPayFundAmount: 'BBBBBB',
            transCurrency: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of AlipayFundAuthInfoRse', async () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            msg: 'BBBBBB',
            subCode: 'BBBBBB',
            subMsg: 'BBBBBB',
            body: 'BBBBBB',
            paramsStr: 'BBBBBB',
            amount: 'BBBBBB',
            authNo: 'BBBBBB',
            creditAmount: 'BBBBBB',
            extraParam: 'BBBBBB',
            fundAmount: 'BBBBBB',
            gmtCreateStr: 'BBBBBB',
            gmtTransStr: 'BBBBBB',
            operationId: 'BBBBBB',
            operationType: 'BBBBBB',
            orderTitle: 'BBBBBB',
            outOrderNo: 'BBBBBB',
            outRequestNo: 'BBBBBB',
            payerLogonId: 'BBBBBB',
            payerUserId: 'BBBBBB',
            preAuthType: 'BBBBBB',
            remark: 'BBBBBB',
            restAmount: 'BBBBBB',
            restCreditAmount: 'BBBBBB',
            restFundAmount: 'BBBBBB',
            status: 'BBBBBB',
            totalFreezeAmount: 'BBBBBB',
            totalFreezeCreditAmount: 'BBBBBB',
            totalFreezeFundAmount: 'BBBBBB',
            totalPayAmount: 'BBBBBB',
            totalPayCreditAmount: 'BBBBBB',
            totalPayFundAmount: 'BBBBBB',
            transCurrency: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AlipayFundAuthInfoRse', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
