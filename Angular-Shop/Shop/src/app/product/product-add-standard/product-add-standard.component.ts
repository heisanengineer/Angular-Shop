import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { categories } from 'src/app/category/categories';
import { AlertifyService } from 'src/app/services/alertify.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { Product } from '../product';

@Component({
  selector: 'app-product-add-standard',
  templateUrl: './product-add-standard.component.html',
  styleUrls: ['./product-add-standard.component.css'],
  providers: [CategoryService,ProductService]
})
export class ProductAddStandardComponent implements OnInit {

  constructor(
    private categoryService:CategoryService,
    private productService:ProductService,
    private alertifyService:AlertifyService) { }
  model: Product = new Product();
  categories!:categories[];

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(data=>{
      this.categories = data;
    });
  }

  add(form:NgForm){
    this.productService.addProduct(this.model).subscribe(data =>{
      this.alertifyService.success(data.name + " added successfully.")
    })
  }
}
