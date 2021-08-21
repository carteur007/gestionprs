import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap,retry } from 'rxjs/operators';
import { User } from '../../models/user';

const USER_API = 'http://localhost:8080';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersUrl: string;

  constructor(private http: HttpClient) { 
    this.usersUrl = USER_API+'/api/users/';
  }
  /**
   * findAll
    * @returns 
    */
  public findAll(): Observable<User[]>{
    return this.http.get<User[]>(`${this.usersUrl}`, httpOptions);
  }
    /**
   * findById  
    * @returns 
    */
    public findById(id: number): Observable<User>{
      return this.http.get<User>(`${this.usersUrl}/show/${id}`, httpOptions)
      .pipe(
        catchError(this.handleError)
      );
    }
    /**
   *  saveUser
   * @param user 
   * @returns 
   */
  public save(user: User) {
    return this.http.post<User>(`${this.usersUrl}`, user, httpOptions)
    .pipe(
      catchError(this.handleError)
    );
  }
  /**
   * updateUser
   * @param user 
   * @returns 
   */
  public update(user: User) {
    return this.http.put<User>(`${this.usersUrl}${user.id}`, user, httpOptions)
    .pipe(
      catchError(this.handleError)
    );
  }
    /**
   * deleteUser
   * @param user 
   * @returns 
   */    
  public delete(id: number) {
    return this.http.delete<User>(`${this.usersUrl}${id}`)
    .pipe(
      catchError(this.handleError)
    );
  }
  /**
   * handleError
   * @param error 
   * @returns 
   */
  private handleError(error: HttpErrorResponse){
    if(error.status === 0){
      console.log('Une erreur c\'est produite');
    }else{
      console.log(`Spring-Boot code:[${error.status}], corps: `,`{{${ error.error}}}`);
    }
     return throwError('Grave erreur veuillez reéssailler plus tard!');
  }


}
