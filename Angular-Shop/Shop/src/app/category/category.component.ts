import { Component, OnInit } from '@angular/core';
import { categories } from './categories';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  constructor() { }

  title = "Kategori Listesi";
  categories: categories[] = [
    { id: 1, name: "Telefon" },
    { id: 2, name: "Tablet" },
    { id: 3, name: "Bilgisayar" }
  ]

  selectedCategory?: categories | null;

  ngOnInit(): void {
  }

  selectCategory(category: categories) {
    this.selectedCategory = category;
  }
}
