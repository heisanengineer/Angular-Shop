import { Component, OnInit } from '@angular/core';
import { Product } from './product';
import { AlertifyService } from '../services/alertify.service';
import { ProductService } from '../services/product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
  providers: [AlertifyService]
})
export class ProductComponent implements OnInit {
  filterText: string | null = "";
  title: string = "Products:";
  products!: Product[];

  constructor(private activatedRoute: ActivatedRoute, private alertifyService: AlertifyService, private productService: ProductService) {
    this.productService.navbarSearchText
      .subscribe((incomingSearchText: string) => this.filterText = incomingSearchText)
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe((params: any) => {
      if (params.categoryId) {
        this.productService.getProductsToCategoryId(params.categoryId).subscribe(data => {
          this.products = data;
        });
      }
      else {
        this.productService.getProducts().subscribe((data: any) => {
          this.products = data
        })
      }

    })
  }

  addToCart(productName: string, productCategoryId: string) {
    this.alertifyService.success(productName + " added. Category: " + productCategoryId);
  }

  getPriceStyle(productPrice: any) {
    return {
      backgroundColor: productPrice < 2500 ? "green" : "red",
      color: "white"
    }
  }

  getPriceBorderStyle(productPrice: any) {
    return {
      border: productPrice < 2500 ? "solid 1px green" : "solid 1px red"
    }
  }

  getPriceButtonStyle(productPrice: any) {
    return {
      border: productPrice < 2500 ? "solid 1px green" : "solid 1px red",
      color: productPrice < 2500 ? "green" : "red",
      backgroundColor:"white"
    }
  }
}
