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


  save(form:any, id:any): Observable<any>{
    return this.http.post(this.groupementUrl+'/'+id, form);
  }

  getGroupement(id:any): Observable<any>{
    return this.http.get(this.groupementUrl+'/'+id);
  }

  updateGroupement(id:any, form: any): Observable<any>{
    return this.http.put(this.groupementUrl+'/'+id, form);
  }
}
