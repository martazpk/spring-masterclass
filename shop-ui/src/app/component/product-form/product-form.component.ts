import {Component, OnInit} from '@angular/core';
import {ProductModel} from "../../models/product.model";
import {ProductService} from "../../service/product.service";
import {Router} from "@angular/router";
import {ProductTypeModel} from "../../models/productType.model";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent {
  product = new ProductModel();
  type: ProductTypeModel;

  constructor(private productService: ProductService, private router: Router) {
  }

  save() {
    this.productService.addProduct(this.product)
      .subscribe(() => this.router.navigateByUrl('products'), error => console.log(error));
  }
}
