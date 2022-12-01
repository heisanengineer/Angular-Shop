import { Component, OnInit } from '@angular/core';
import { Product } from './product';
import { AlertifyService } from '../services/alertify.service';
import { ProductService } from '../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { faCartPlus, faTag, faList } from '@fortawesome/free-solid-svg-icons';
import { CategoryService } from '../services/category.service';
import { categories } from '../category/categories';
import { SearchBarService } from '../services/search-bar.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
  providers: [AlertifyService, CategoryService,ProductService]
})
export class ProductComponent implements OnInit {
  filterText: string | null = "";
  title: string = "Products:";
  products!: Product[];
  categories?: categories[];

  faCartPlus = faCartPlus;
  faTag = faTag;
  faList = faList;

  constructor(
    private activatedRoute: ActivatedRoute,
    private alertifyService: AlertifyService,
    private searchBar: SearchBarService,
    private categoryService: CategoryService,
    private productService:ProductService
  ) {
    this.searchBar.navbarSearchText
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
        });
      }
    });

    this.getAllCategories();
  }

  getAllCategories() {
    this.categoryService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }
  addToCart(productName: string) {
    this.alertifyService.success(productName + " added to cart");
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
      backgroundColor: "white"
    }
  }
}
