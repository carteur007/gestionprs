import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {UserServiceService} from "../../user-service.service";
import {Router} from "@angular/router";
import {isEmpty} from "rxjs/internal/operators";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  form: any = {};

  provinces: string[] = [];
  communes: string[] = [];
  centres: string[] = [];
  uploadedFile: any ={};
  message: any ;

  constructor(private userService: UserServiceService, private router: Router) { }

  ngOnInit(): void {
    this.provinces = ['Province 1','Province 2','Province 3','Province 4','Province 5','Province 6','Province 7','Province 8','Province 9','Province 10'];
    this.communes = ['Commune 1','Commune 2','Commune 3','Commune 4','Commune 5','Commune 6','Commune 7','Commune 8','Commune 9','Commune 10'];
    this.centres = ['Centre formation 1','Centre formation 2','Centre formation 3','Centre formation 4','Centre formation 5','Centre formation 6',
      'Centre formation 7','Centre formation 8','Centre formation 9','Centre formation 10'];
    this.init();
  }

  init(){
    this.form = new FormGroup(
      {
        id: new FormControl(''),
        nom: new FormControl(''),
        prenom: new FormControl(''),
        nomMere: new FormControl(''),
        nomPere: new FormControl(''),
        email: new FormControl(''),
        matricule: new FormControl(''),
        telephone: new FormControl(''),
        region: new FormControl(''),
        arrondissement: new FormControl(''),
        dateNaissance: new FormControl(null),
        dateEntree: new FormControl(null),
        centreFormation: new FormControl(''),
        profile: new FormControl(null),
        sexe: new FormControl(''),
        statusMatrimoniale: new FormControl(''),
        fonction: new FormControl(''),
        ville: new FormControl(''),
        quartier: new FormControl(''),
        legion: new FormControl(''),
        groupement: new FormControl(''),
        grade: new FormControl('')
      }
    );
  }

  onFilesSelect(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      console.log(file.name);
      if(!this.validateFile(file.name)){
        this.message = 'File should be an image, please load image';
        //this.isValid = false;
      }else{
        this.uploadedFile = file;
        //this.isValid =  true;
      }
    }
  }

  validateFile(name: String) {
    var ext = name.substring(name.lastIndexOf('.') + 1);
    if (ext.toLowerCase() == 'png'||ext.toLowerCase() == 'jpg'||ext.toLowerCase() == 'jpeg') {
      return true;
    }
    else {
      return false;
    }
  }

  onSubmit(){
    const  formData = new FormData();
    formData.append('nom',this.form.get('nom').value);
    formData.append('prenom',this.form.get('prenom').value);
    formData.append('nomMere',this.form.get('nomMere').value);
    formData.append('nomPere',this.form.get('nomPere').value);
    formData.append('sexe',this.form.get('sexe').value);
    formData.append('email',this.form.get('email').value);
    formData.append('matricule',this.form.get('matricule').value);
    formData.append('telephone',this.form.get('telephone').value);
    formData.append('region',this.form.get('region').value);
    formData.append('arrondissement',this.form.get('arrondissement').value);
    formData.append('dateNaissance',this.form.get('dateNaissance').value);
    formData.append('dateEntree',this.form.get('dateEntree').value);
    formData.append('centreFormation',this.form.get('centreFormation').value);
    formData.append('statusMatrimoniale',this.form.get('statusMatrimoniale').value);
    formData.append('file',this.uploadedFile);
    console.log(this.uploadedFile);
    /*if(this.isEmpty(this.uploadedFile)){
      this.message = 'Empty file, please upload image file and try again';
    }else {*/
    console.log(formData);
    this.userService.save(formData).subscribe(
      data =>{
        console.log(data);
        this.router.navigateByUrl('users/users');

      },
      error => {
        console.log(error);
      }
    );
    //}
  }

  isEmpty(obj: any) {
    for(var key in obj) {
      if(obj.hasOwnProperty(key))
        return false;
    }
    return true;
  }

}
