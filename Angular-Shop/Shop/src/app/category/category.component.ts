import { Component, OnInit } from '@angular/core';
import { categories } from './categories';
import { CategoryService } from '../services/category.service';
import { faYoutube } from '@fortawesome/free-brands-svg-icons';
import { } from '@fortawesome/fontawesome-svg-core';
import { } from '@fortawesome/free-regular-svg-icons';
import { faArrowRight} from '@fortawesome/free-solid-svg-icons';

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

  title = "Categories";
  categories!: categories[];

  faArrowRight = faArrowRight;

  selectedCategory?: categories | null;

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(data => {
      this.categories = data
    });
  }
  public category: any;

  selectCategory(category: categories) {
    this.selectedCategory = category;

  }

}
