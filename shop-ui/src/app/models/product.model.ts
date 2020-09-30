import {ProductTypeModel} from "./productType.model";

export class ProductModel {

  name: string;
  description: string;
  price: string;
  type: ProductTypeModel;
  keys = Object.keys;
}
