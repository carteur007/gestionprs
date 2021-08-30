import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LegionService } from 'src/app/service/legion.service';

@Component({
  selector: 'app-legion-add',
  templateUrl: './legion-add.component.html',
  styleUrls: ['./legion-add.component.css']
})
export class LegionAddComponent implements OnInit {

  legionForm: any={};
  villes: string[]=[];
  constructor(private legionService: LegionService, private router: Router) { }

  ngOnInit(): void {
    this.villes = ['Ville 1','Ville 2','Ville 3','Ville 4','Ville 5','Ville 6'];
    this.init();
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

    this.legionService.save(formData).subscribe(
      data => {
        console.log(data);
        this.router.navigateByUrl('legions/legions');
      },
      error => {
        console.log(error);
      }
    );
  }


  

}
