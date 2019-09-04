/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { CommodityRseService } from 'app/entities/commodity-rse/commodity-rse.service';
import { ICommodityRse, CommodityRse, DeliveryMethod, CommodityStatus } from 'app/shared/model/commodity-rse.model';

describe('Service Tests', () => {
  describe('CommodityRse Service', () => {
    let injector: TestBed;
    let service: CommodityRseService;
    let httpMock: HttpTestingController;
    let elemDefault: ICommodityRse;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(CommodityRseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CommodityRse(
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        0,
        0,
        DeliveryMethod.ExpressDelivery,
        CommodityStatus.OnLine,
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

      it('should create a CommodityRse', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new CommodityRse(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a CommodityRse', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            remark: 'BBBBBB',
            dayRent: 1,
            weeklyRent: 1,
            monthlyRent: 1,
            deposit: 1,
            rentalMethod: 'BBBBBB',
            maxRent: 1,
            inventory: 1,
            deliveryMethod: 'BBBBBB',
            status: 'BBBBBB',
            leaseMustRead: 'BBBBBB',
            desciption: 'BBBBBB'
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

      it('should return a list of CommodityRse', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            remark: 'BBBBBB',
            dayRent: 1,
            weeklyRent: 1,
            monthlyRent: 1,
            deposit: 1,
            rentalMethod: 'BBBBBB',
            maxRent: 1,
            inventory: 1,
            deliveryMethod: 'BBBBBB',
            status: 'BBBBBB',
            leaseMustRead: 'BBBBBB',
            desciption: 'BBBBBB'
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

      it('should delete a CommodityRse', async () => {
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
