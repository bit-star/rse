/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AlipayFreezeResponseRseService } from 'app/entities/alipay-freeze-response-rse/alipay-freeze-response-rse.service';
import { IAlipayFreezeResponseRse, AlipayFreezeResponseRse } from 'app/shared/model/alipay-freeze-response-rse.model';

describe('Service Tests', () => {
  describe('AlipayFreezeResponseRse Service', () => {
    let injector: TestBed;
    let service: AlipayFreezeResponseRseService;
    let httpMock: HttpTestingController;
    let elemDefault: IAlipayFreezeResponseRse;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(AlipayFreezeResponseRseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AlipayFreezeResponseRse(
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

      it('should create a AlipayFreezeResponseRse', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new AlipayFreezeResponseRse(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a AlipayFreezeResponseRse', async () => {
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
            fundAmount: 'BBBBBB',
            gmtTrans: 'BBBBBB',
            operationId: 'BBBBBB',
            outOrderNo: 'BBBBBB',
            outRequestNo: 'BBBBBB',
            payerUserId: 'BBBBBB',
            preAuthType: 'BBBBBB',
            status: 'BBBBBB'
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

      it('should return a list of AlipayFreezeResponseRse', async () => {
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
            fundAmount: 'BBBBBB',
            gmtTrans: 'BBBBBB',
            operationId: 'BBBBBB',
            outOrderNo: 'BBBBBB',
            outRequestNo: 'BBBBBB',
            payerUserId: 'BBBBBB',
            preAuthType: 'BBBBBB',
            status: 'BBBBBB'
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

      it('should delete a AlipayFreezeResponseRse', async () => {
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
