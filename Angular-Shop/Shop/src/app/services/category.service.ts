import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import {tap,catchError} from 'rxjs/operators';
import { throwError } from 'rxjs';
import { categories } from '../category/categories';

@Injectable()
export class CategoryService {

  constructor(private http: HttpClient) { }

  path = "http://localhost:3000/categories";

  getCategories(): Observable<categories[]> {
    return this.http
      .get<categories[]>(this.path).pipe(
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
