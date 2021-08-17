import { Component, OnInit } from '@angular/core';
import {UserServiceService} from "../../user-service.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-detail-user',
  templateUrl: './detail-user.component.html',
  styleUrls: ['./detail-user.component.css']
})
export class DetailUserComponent implements OnInit {

  user: any = {};

  constructor(private userService: UserServiceService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getUser(this.activatedRoute.snapshot.paramMap.get('id'));
  }



  getUser(id:any){
    this.userService.getOne(id).subscribe(
      data => {
        this.user = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }

}
