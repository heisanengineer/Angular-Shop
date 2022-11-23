import { getCurrencySymbol } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],

})
export class NavComponent {
  navbarSearchText:string=""
  constructor(
    private productService: ProductService
    ) { }


  getSearchText(event: any) {
    let searchText = String(event.value)
    this.productService.navbarSearchText.next(searchText)
  }

  getColor(){
    return 'green';
  }
}


