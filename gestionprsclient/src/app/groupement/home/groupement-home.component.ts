import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GroupementService } from 'src/app/service/groupement.service';
import { LegionService } from 'src/app/service/legion.service';

@Component({
  selector: 'app-groupement-home',
  templateUrl: './groupement-home.component.html',
  styleUrls: ['./groupement-home.component.css']
})
export class GroupementHomeComponent implements OnInit {

  lists: any ={};
  currentLegion: any;
  p:number = 1;
  searchItem: string = '';
  constructor(private activatedRouted: ActivatedRoute, private groupementService: GroupementService,
    private legionService: LegionService) { }

  ngOnInit(): void {
    this.loadAllByLegion(this.activatedRouted.snapshot.paramMap.get('id'));
    this.getCurrentLegion(this.activatedRouted.snapshot.paramMap.get('id'))
  }

  loadAllByLegion(id:any){
    this.groupementService.getAllByLegion(id).subscribe(
      data => {
        this.lists = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }

  getCurrentLegion(id:any){
this.legionService.getOne(id).subscribe(
  data => {
    this.currentLegion = data;
    console.log(data);
  },
  error => {
    console.log(error);
  }
);
  }
}
