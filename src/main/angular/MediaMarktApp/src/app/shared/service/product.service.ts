import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  getAllProductsByCategoryName(categoryName: string): Observable<any> {
    return this.http.get('//localhost:8081/products/category/' + categoryName);
  }
}
