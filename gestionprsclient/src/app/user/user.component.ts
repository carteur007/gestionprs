import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { UserService } from './../services/user/user-service.service';
import { Router } from '@angular/router';
import { User } from './../models/user';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Location,DatePipe } from '@angular/common';

@Component({
  selector: 'app-user',
  providers: [DatePipe],
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: User[] = [];
  user!: User;
  allMsg:string = "";
  @ViewChild('closebutton') closebutton: any;
  constructor(private  formBuilder: FormBuilder,
    private location: Location,
    private router: Router, private userService:UserService, private datePipe: DatePipe) {

  }

  public getusers(){
    this.userService.findAll()
    .subscribe(users => {this.users = users});
  }

  public saveUser(){
    this.router.navigate(['add-user'])
    .then((e) =>{
      if(e)
        console.log('Navigation Reussi!');
      else
        console.log('Echec de la navigation!');
    });
  }
  public deleteUser(userId: number){
    this.userService.delete(userId)
    .subscribe(response => {
      this.getusers();
    }, error => {
      this.allMsg = error;
    })
  }
  updateForm = this.formBuilder.group({
    id: new FormControl({value:'', disabled:true}),
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
  public updateUser(userId:number){
    this.userService.findById(userId)
    .subscribe(data => {
      this.user = data;
      this.prepareUpdateForm();
    })
  }
  prepareUpdateForm() {
    this.updateForm.setValue({
      id: this.user.id,
      nom: this.user.nom,
      prenom: this.user.prenom,
      nomMere: this.user.nomMere,
      nomPere: this.user.nomPere, 
      email: this.user.email,
      matricule: this.user.matricule,
      telephone: this.user.telephone,
      lieuNaissance: this.user.lieuNaissance,
      dateNaissance: this.datePipe.transform(this.user.dateNaissance, 'yyyy-MM-dd'), 
      dateEntree: this.datePipe.transform(this.user.dateEntree, 'yyyy-MM-dd'),
      centreFormation: this.user.centreFormation,
      region: this.user.centreFormation, 
      arrondissement: this.user.arrondissement
    });
  }
  
  public getUser(){
    let user = new User();
    user.id= this.updateForm.getRawValue().id;
    user.nom= this.updateForm.value.nom;
    user.prenom= this.updateForm.value.prenom;
    user.nomMere= this.updateForm.value.nomMere;
    user.nomPere= this.updateForm.value.nomPere; 
    user.email= this.updateForm.value.email;
    user.matricule= this.updateForm.value.matricule;
    user.telephone= this.updateForm.value.telephone;
    user.lieuNaissance= this.updateForm.value.lieuNaissance;
    user.dateNaissance= this.updateForm.value.dateNaissance;
    user.dateEntree= this.updateForm.value.dateEntree;
    user.centreFormation= this.updateForm.value.centreFormation;
    user.region= this.updateForm.value.centreFormation;
    user.arrondissement= this.updateForm.value.arrondissement
    return user;
  }
  onSubmit(){
    let user = this.getUser();
    this.userService.update(user)
      .subscribe(data =>{
        this.closebutton.nativeElement.click();
        this.getusers();
        this.goBack();
        this.router.navigateByUrl('/');
      },
      error => console.log(error));
      
  }

  ngOnInit(){
    this.getusers();
  }
  /**
   * goBack
   */
  public goBack() {
    this.location.back();
  }
}
