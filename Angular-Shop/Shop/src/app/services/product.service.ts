import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Product } from '../product/product';
import { Observable } from 'rxjs/internal/Observable';
import { tap, catchError } from 'rxjs/operators';
import { BehaviorSubject, throwError } from 'rxjs';
import { CategoryComponent } from '../category/category.component';
import { AlertifyService } from './alertify.service';

@Injectable({providedIn:"root"})
export class ProductService {

  navbarSearchText = new BehaviorSubject("")

  constructor(private http: HttpClient) {
  }

  path = "http://localhost:3000/products";

  getProductsToCategoryId(categoryId:any): Observable<Product[]> {
    return this.http
      .get<Product[]>(this.path + "?categoryId="+ categoryId)
      .pipe( tap(data => console.log(JSON.stringify(data))),
        catchError(this.handleError)
      );
  }
  getProducts():Observable<Product[]>{
    return this.http.get<Product[]>(this.path).pipe(tap(data => console.log(JSON.stringify(data))),
    catchError(this.handleError))
  }
  
  handleError(err: HttpErrorResponse) {
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      errorMessage = "We have a problem." + err.error.message;
    } else {
      errorMessage = "System crash.";
    }
    return throwError(errorMessage);
  }
}
