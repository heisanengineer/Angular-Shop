import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Product } from '../product/product';
import { Observable } from 'rxjs/internal/Observable';
import { tap, catchError } from 'rxjs/operators';
import { BehaviorSubject, throwError } from 'rxjs';

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) {
  }

  path = "http://localhost:3000/products";
  newPath = "http://localhost:3000/productadd/";

  getProductsToCategoryId(categoryId: any): Observable<Product[]> {
    return this.http
      .get<Product[]>(this.path + "?categoryId=" + categoryId)
      .pipe(tap(data => console.log(JSON.stringify(data))),
        catchError(this.handleError)
      );
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.path).pipe(
      tap(data => console.log(JSON.stringify(data))),
      catchError(this.handleError)
    );
  }

  addProduct(product: Product): Observable<Product> {
    //const headers={'content-type':'application/json'}
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Token'
      })
    }
 
    return this.http.post<Product>(this.path, product, httpOptions)
      .pipe(tap(data => console.log(JSON.stringify(data))),
        catchError(this.handleError)
      );
  }

  handleError(err: HttpErrorResponse) {
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      errorMessage = "We have a problem." + err.error.message;
    } else {
      errorMessage = err.error.message;
    }
    return throwError(errorMessage);
  }
}
