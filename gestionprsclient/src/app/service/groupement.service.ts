import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GroupementService {

  private groupementUrl: string;

  constructor(private http: HttpClient) { 
    this.groupementUrl = 'http://localhost:8080/api/groupements';
  }


  getAllByLegion(id:any):Observable<any>{
return this.http.get(this.groupementUrl+'/legion/'+id);
  }
}
