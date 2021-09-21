import {ProductModel} from "./product.model";

export interface CategoryModel {

  name: string;
  products: ProductModel[] ;
}
