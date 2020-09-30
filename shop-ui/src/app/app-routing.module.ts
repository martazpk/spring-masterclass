import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UsersListComponent} from "./component/users-list/users-list.component";
import {UserFormComponent} from "./component/user-form/user-form.component";
import {ProductListComponent} from "./component/product-list/product-list.component";
import {ProductFormComponent} from "./component/product-form/product-form.component";

const routes: Routes = [
  {
    path: 'users',
    component: UsersListComponent
  },
  {
    path: 'add-user',
    component: UserFormComponent
  },
  {
    path: 'products',
    component: ProductListComponent
  },
  {
    path: 'add-product',
    component: ProductFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
