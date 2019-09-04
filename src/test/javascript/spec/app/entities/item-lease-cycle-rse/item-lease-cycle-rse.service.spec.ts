/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ItemLeaseCycleRseService } from 'app/entities/item-lease-cycle-rse/item-lease-cycle-rse.service';
import { IItemLeaseCycleRse, ItemLeaseCycleRse } from 'app/shared/model/item-lease-cycle-rse.model';

describe('Service Tests', () => {
  describe('ItemLeaseCycleRse Service', () => {
    let injector: TestBed;
    let service: ItemLeaseCycleRseService;
    let httpMock: HttpTestingController;
    let elemDefault: IItemLeaseCycleRse;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ItemLeaseCycleRseService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ItemLeaseCycleRse(0, 'AAAAAAA', currentDate, currentDate, 0, 0, 0, 0, 0, 0, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            startTime: currentDate.format(DATE_TIME_FORMAT),
            endTime: currentDate.format(DATE_TIME_FORMAT),
            nextBillDay: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a ItemLeaseCycleRse', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            startTime: currentDate.format(DATE_TIME_FORMAT),
            endTime: currentDate.format(DATE_TIME_FORMAT),
            nextBillDay: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startTime: currentDate,
            endTime: currentDate,
            nextBillDay: currentDate
          },
          returnedFromService
        );
        service
          .create(new ItemLeaseCycleRse(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ItemLeaseCycleRse', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            startTime: currentDate.format(DATE_TIME_FORMAT),
            endTime: currentDate.format(DATE_TIME_FORMAT),
            inventory: 1,
            numberOfPeriods: 1,
            deposit: 1,
            creditExemption: 1,
            rentReceivedNumberOfPeriods: 1,
            debitedAmount: 1,
            nextBillDay: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startTime: currentDate,
            endTime: currentDate,
            nextBillDay: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of ItemLeaseCycleRse', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            startTime: currentDate.format(DATE_TIME_FORMAT),
            endTime: currentDate.format(DATE_TIME_FORMAT),
            inventory: 1,
            numberOfPeriods: 1,
            deposit: 1,
            creditExemption: 1,
            rentReceivedNumberOfPeriods: 1,
            debitedAmount: 1,
            nextBillDay: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startTime: currentDate,
            endTime: currentDate,
            nextBillDay: currentDate
          },
          returnedFromService
        );
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

      it('should delete a ItemLeaseCycleRse', async () => {
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
