import { DatePipe } from '@angular/common';
import { UserService } from './../../services/user/user-service.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-add',
  providers: [DatePipe],
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {

  addForm!: FormGroup;
  constructor(private datepipe: DatePipe, private userS: UserService, private formBuilder: FormBuilder, private router: Router) { }

  onSubmit() {
    this.userS.save(this.addForm.value)
      .subscribe(data =>{
        this.router.navigate(['app-user']);
      });
  }
  ngOnInit():void{
    this.addForm= this.formBuilder.group({
      id: [],
      nom: ['',Validators.required],
      prenom: ['',Validators.required],
      nomMere: ['',Validators.required],
      nomPere: ['',Validators.required], 
      email: ['',Validators.required],
      matricule: ['',Validators.required],
      telephone: ['',Validators.required],
      lieuNaissance: ['',Validators.required],
      dateNaissance: ['',Validators.required], 
      dateEntree: ['',Validators.required],
      centreFormation: ['',Validators.required],
      region: ['',Validators.required], 
      arrondissement: ['',Validators.required]
  
    });
  }

}
