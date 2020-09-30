import {environment} from "../environments/environment";

export class Api{
  users= `${environment.baseUrl}users`;
  productList= `${environment.baseUrl}products/all`;
  products= `${environment.baseUrl}products`;
}
