import { Component } from '@angular/core';
import {CategoryModel} from "./shared/model/category.model";
import {ProductModel} from "./shared/model/product.model";
import {CategoryService} from "./shared/service/category.service";
import {ProductService} from "./shared/service/product.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public categories: CategoryModel[] = [];
  public selectedCategory: CategoryModel = {} as CategoryModel;
  public products: ProductModel[] = [];
  public filteredProducts: ProductModel[] = [];
  public selectedProduct: ProductModel = {} as ProductModel;
  public brand: string = '';

  public constructor(private categoryService: CategoryService,
                     private productService:ProductService) { }

  public ngOnInit(): void {
    this.categoryService.getAllCategories().subscribe(data => {
      this.categories = data;
    });
  }

  public listProduct(categoryName: string): void {
    this.productService.getAllProductsByCategoryName(categoryName).subscribe(data => {
      this.products = data;
      this.filteredProducts = data;
    });
  }

  public searchByBrand(): void {
    this.filteredProducts = this.products.filter(product => product.brand === this.brand);
    this.selectedProduct = {} as ProductModel;
  }
}
