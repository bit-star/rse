/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { UserOrderRseService } from 'app/entities/user-order-rse/user-order-rse.service';
import { IUserOrderRse, UserOrderRse, OrderStatus } from 'app/shared/model/user-order-rse.model';

describe('Service Tests', () => {
  describe('UserOrderRse Service', () => {
    let injector: TestBed;
    let service: UserOrderRseService;
    let httpMock: HttpTestingController;
    let elemDefault: IUserOrderRse;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(UserOrderRseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new UserOrderRse(
        0,
        'AAAAAAA',
        'AAAAAAA',
        OrderStatus.PendingPayment,
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
        0,
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

      it('should create a UserOrderRse', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new UserOrderRse(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a UserOrderRse', async () => {
        const returnedFromService = Object.assign(
          {
            orderNo: 'BBBBBB',
            requestNo: 'BBBBBB',
            status: 'BBBBBB',
            receiver: 'BBBBBB',
            mobile: 'BBBBBB',
            province: 'BBBBBB',
            city: 'BBBBBB',
            region: 'BBBBBB',
            address: 'BBBBBB',
            storeName: 'BBBBBB',
            logisticsCompany: 'BBBBBB',
            shipmentNumber: 'BBBBBB',
            paymentMethod: 'BBBBBB',
            freight: 1,
            description: 'BBBBBB',
            processingOpinions: 'BBBBBB'
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

      it('should return a list of UserOrderRse', async () => {
        const returnedFromService = Object.assign(
          {
            orderNo: 'BBBBBB',
            requestNo: 'BBBBBB',
            status: 'BBBBBB',
            receiver: 'BBBBBB',
            mobile: 'BBBBBB',
            province: 'BBBBBB',
            city: 'BBBBBB',
            region: 'BBBBBB',
            address: 'BBBBBB',
            storeName: 'BBBBBB',
            logisticsCompany: 'BBBBBB',
            shipmentNumber: 'BBBBBB',
            paymentMethod: 'BBBBBB',
            freight: 1,
            description: 'BBBBBB',
            processingOpinions: 'BBBBBB'
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

      it('should delete a UserOrderRse', async () => {
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
