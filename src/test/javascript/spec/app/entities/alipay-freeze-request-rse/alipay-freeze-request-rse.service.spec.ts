/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AlipayFreezeRequestRseService } from 'app/entities/alipay-freeze-request-rse/alipay-freeze-request-rse.service';
import { IAlipayFreezeRequestRse, AlipayFreezeRequestRse } from 'app/shared/model/alipay-freeze-request-rse.model';

describe('Service Tests', () => {
  describe('AlipayFreezeRequestRse Service', () => {
    let injector: TestBed;
    let service: AlipayFreezeRequestRseService;
    let httpMock: HttpTestingController;
    let elemDefault: IAlipayFreezeRequestRse;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(AlipayFreezeRequestRseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AlipayFreezeRequestRse(
        0,
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

      it('should create a AlipayFreezeRequestRse', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new AlipayFreezeRequestRse(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a AlipayFreezeRequestRse', async () => {
        const returnedFromService = Object.assign(
          {
            orderTitle: 'BBBBBB',
            outOrderNo: 'BBBBBB',
            outRequestNo: 'BBBBBB',
            payeeUserId: 'BBBBBB',
            payeeLogonId: 'BBBBBB',
            productCode: 'BBBBBB',
            amount: 'BBBBBB',
            extraParam: 'BBBBBB',
            notifyUrl: 'BBBBBB'
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

      it('should return a list of AlipayFreezeRequestRse', async () => {
        const returnedFromService = Object.assign(
          {
            orderTitle: 'BBBBBB',
            outOrderNo: 'BBBBBB',
            outRequestNo: 'BBBBBB',
            payeeUserId: 'BBBBBB',
            payeeLogonId: 'BBBBBB',
            productCode: 'BBBBBB',
            amount: 'BBBBBB',
            extraParam: 'BBBBBB',
            notifyUrl: 'BBBBBB'
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

      it('should delete a AlipayFreezeRequestRse', async () => {
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
