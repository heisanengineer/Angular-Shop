import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Product } from '../product/product';
import { Observable } from 'rxjs/internal/Observable';
import {tap,catchError} from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) { }

  path = "http://localhost:3000/products";

  getProducts(): Observable<Product[]> {
    return this.http
      .get<Product[]>(this.path).pipe(
        tap(data=>console.log(JSON.stringify(data))),
        catchError(this.handleError)
      );
  }

  handleError(err: HttpErrorResponse){
    let errorMessage ='';
    if(err.error instanceof ErrorEvent){
      errorMessage = "We have a problem." + err.error.message;
    }else{
      errorMessage="System crash.";
    }
    return throwError(errorMessage);
  }
}
