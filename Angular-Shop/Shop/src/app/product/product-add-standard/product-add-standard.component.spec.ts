import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductAddStandardComponent } from './product-add-standard.component';

describe('ProductAddStandardComponent', () => {
  let component: ProductAddStandardComponent;
  let fixture: ComponentFixture<ProductAddStandardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductAddStandardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductAddStandardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
