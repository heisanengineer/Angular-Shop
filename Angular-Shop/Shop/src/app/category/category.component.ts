import { Component, OnInit } from '@angular/core';
import { categories } from './categories';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
  providers: [CategoryService]
})
export class CategoryComponent implements OnInit {
  constructor(
    private categoryService: CategoryService) {
  }

  title = "Categories:";
  categories!: categories[];

  selectedCategory?: categories | null;

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(data => {
      this.categories = data
    });
  }

  selectCategory(category: categories) {
    this.selectedCategory = category;

  }

}
