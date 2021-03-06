import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/api/users';
  }
  /**
   * findAll
    * @returns
    */
  public findAll(): Observable<User[]>{
    return this.http.get<User[]>(this.usersUrl);
  }
  /**
   *
   * @param user
   * @returns
   */
  public save(user:any): Observable<any> {
    console.log('je suis la');
    return this.http.post<User>(this.usersUrl, user);
  }

  /**
   *
   *  findOne
   * @returns
   */
  public getOne(id: any): Observable<any> {
    console.log('je suis la');
    return this.http.get(this.usersUrl+'/'+id);
  }

}
