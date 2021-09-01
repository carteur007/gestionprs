import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GroupementService } from 'src/app/service/groupement.service';
import { LegionService } from 'src/app/service/legion.service';

@Component({
  selector: 'app-groupement-add',
  templateUrl: './groupement-add.component.html',
  styleUrls: ['./groupement-add.component.css']
})
export class GroupementAddComponent implements OnInit {

  currentLegion: any = {};
  groupementForm: any;
  villes: string []=[];
  constructor(private groupementService: GroupementService, private legionService: LegionService,
    private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.villes = ['Ville 1','Ville 2','Ville 3','Ville 4','Ville 5','Ville 6'];
    this.getLegion(this.activatedRoute.snapshot.paramMap.get('id'));
    this.init();
  }


  init(){
    this.groupementForm = new FormGroup({
      nom: new FormControl(''),
      ville: new FormControl(''),
      code: new FormControl(''),
      pseudo: new FormControl(''),
    });
  }


  getLegion(id:any){
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

  onSubmit(){
    const formData = {
      nom: this.groupementForm.get('nom').value,
      ville: this.groupementForm.get('ville').value,
      code: this.groupementForm.get('code').value,
      pseudo: this.groupementForm.get('pseudo').value
    };
    console.log(formData);

    this.groupementService.save(formData, this.currentLegion.id).subscribe(
      data => {
        console.log(data);
        this.router.navigateByUrl('groupements/groupements/'+this.currentLegion.id);
      },
      error => {
        console.log(error);
      }
    );
  }

}
