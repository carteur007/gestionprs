import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LegionService {

  private legionUrl: string;
  constructor(private http: HttpClient) {
    this.legionUrl = 'http://localhost:8080/api/legions';
   }

   getAll(): Observable<any>{
     return this.http.get(this.legionUrl);
   }

   save(form: any): Observable<any>{
    return this.http.post(this.legionUrl, form);
   }
}
