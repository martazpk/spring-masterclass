import { Component, OnInit } from '@angular/core';
import {PagedResultModel} from "../../models/paged-result.model";
import {ProductModel} from "../../models/product.model";
import {ProductService} from "../../service/product.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent{
  pagedResult: PagedResultModel<ProductModel>;

  constructor(private productService: ProductService) {
    this.reload(0);
  }

  previous() {
    this.reload(this.pagedResult.pageNumber - 1);
  }

  next() {
    this.reload(this.pagedResult.pageNumber + 1);
  }

  private reload(pageNumber: number) {
    this.productService.getProducts(pageNumber)
      .subscribe(pageResult => this.pagedResult= pageResult, error => console.log(error));
  }
}
