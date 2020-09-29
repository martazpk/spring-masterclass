import {Injectable} from "@angular/core";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {UserModel} from "../models/user.model";
import {Observable} from "rxjs";
import {Api} from "../api";
import {PagedResultModel} from "../models/paged-result.model";

@Injectable()
export class UserService {

  constructor(private httpClient: HttpClient, private api: Api) {
  }

  addUser(user: UserModel): Observable<UserModel> {
    return this.httpClient.post<UserModel>(this.api.users, user)
  }

  getUsers(pageNumber: Number = 0, pageSize: Number = 5): Observable<PagedResultModel<UserModel>> {
    const params = {pageNumber:`${pageNumber}`, pageSize: `${pageSize}`, surnameFragment: ""}
    return this.httpClient.get<PagedResultModel<UserModel>>(this.api.users, { params })
  }
}
