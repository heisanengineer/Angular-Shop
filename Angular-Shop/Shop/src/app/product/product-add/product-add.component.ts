import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { categories } from 'src/app/category/categories';
import { AlertifyService } from 'src/app/services/alertify.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { Product } from '../product';

@Component({
  selector: 'product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css'],
  providers: [CategoryService, ProductService]
})
export class ProductAddComponent implements OnInit {


  constructor(
    private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private productService: ProductService,
    private alertifyService: AlertifyService,
  ) { }

  productAddForm!: FormGroup;
  model: Product = new Product();
  categories: categories[] = [];
  selectedCategory: any


  createProductAddForm() {
    this.productAddForm = this.formBuilder.group({
      id: [],
      name: ["", Validators.required],
      description: ["", Validators.required],
      imageUrl: ["", Validators.required],
      price: ["", Validators.required],
      categoryId: ["", Validators.required]
    });
  }

  ngOnInit(): void {
    this.createProductAddForm();

    this.categoryService.getCategories().subscribe(data => {
      this.categories = data

    });
    this.productAddForm.valueChanges.subscribe(formData => {
      this.selectedCategory = this.categories.find(x => x?.name === formData.categoryId);
    })
  }

  add() {
    let formData = this.productAddForm.value;
    formData.categoryId = this.selectedCategory.id;
    if (this.productAddForm.valid) {
      this.productService.addProduct(formData)
        .subscribe(data => {
          this.alertifyService.success(data.name + " Product Added.")
        });
    }
  }
}
