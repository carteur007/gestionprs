import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GroupementService } from 'src/app/service/groupement.service';

@Component({
  selector: 'app-groupement-update',
  templateUrl: './groupement-update.component.html',
  styleUrls: ['./groupement-update.component.css']
})
export class GroupementUpdateComponent implements OnInit {

  currentGroupement: any;
  form: any;
  villes: string[]=[];
  constructor(private groupementService: GroupementService, private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.getGroupement(this.activatedRoute.snapshot.paramMap.get('id'));
    this.villes = ['Ville 1','Ville 2','Ville 3','Ville 4','Ville 5','Ville 6'];
    this.init();
  }


  getGroupement(id:any){
    this.groupementService.getGroupement(id).subscribe(
      data => {
        this.currentGroupement = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }

  init(){
    this.form = new FormGroup({
      nom: new FormControl(''),
      ville: new FormControl(''),
      code: new FormControl(''),
      pseudo: new FormControl(''),
    });
  }

  onSubmit(){
    const formData = {
      nom: this.form.get('nom').value,
      ville: this.form.get('ville').value,
      code: this.form.get('code').value,
      pseudo: this.form.get('pseudo').value
    };
    console.log(formData);

    this.groupementService.updateGroupement(this.currentGroupement.id,formData).subscribe(
      data => {
        console.log(data);
        console.log(data.legion.id);
        this.router.navigateByUrl('groupements/groupements/'+data.legion.id);
      },
      error => {
        console.log(error);
      }
    );
  }

}
