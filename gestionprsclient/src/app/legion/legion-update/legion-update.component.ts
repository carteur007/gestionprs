import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LegionService } from 'src/app/service/legion.service';

@Component({
  selector: 'app-legion-update',
  templateUrl: './legion-update.component.html',
  styleUrls: ['./legion-update.component.css']
})
export class LegionUpdateComponent implements OnInit {

  legionForm: any={};
  currentLegion: any = {};
  villes: string[]=[];
  constructor(private legionService: LegionService, private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getLegion(this.activatedRoute.snapshot.paramMap.get('id'));
    this.init();
    this.villes = ['Ville 1','Ville 2','Ville 3','Ville 4','Ville 5','Ville 6'];
  }

  init(){
    this.legionForm = new FormGroup({
      nom: new FormControl(''),
      ville: new FormControl(''),
      code: new FormControl('')
    });
  }


  onSubmit(){
    const formData = {
      nom: this.legionForm.get('nom').value,
      ville: this.legionForm.get('ville').value,
      code: this.legionForm.get('code').value
    };
    console.log(formData);

    this.legionService.update(formData, this.currentLegion.id).subscribe(
      data => {
        console.log(data);
        this.router.navigateByUrl('legions/legions');
      },
      error => {
        console.log(error);
      }
    );
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

}
