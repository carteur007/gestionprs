import { Component, OnInit } from '@angular/core';
import { LegionService } from 'src/app/service/legion.service';

@Component({
  selector: 'app-legion-home',
  templateUrl: './legion-home.component.html',
  styleUrls: ['./legion-home.component.css']
})
export class LegionHomeComponent implements OnInit {

  lists: any ={};
  p: number=1;
  searchItem: string = '';
  
  constructor( private legionService: LegionService) { }

  ngOnInit(): void {
    this.getAll();
    
  }

  getAll(){
    this.legionService.getAll().subscribe(
      data => {
        this.lists = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }

}
