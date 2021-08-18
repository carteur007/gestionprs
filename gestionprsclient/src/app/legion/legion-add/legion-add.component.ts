import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { LegionService } from 'src/app/service/legion.service';

@Component({
  selector: 'app-legion-add',
  templateUrl: './legion-add.component.html',
  styleUrls: ['./legion-add.component.css']
})
export class LegionAddComponent implements OnInit {

  form: any={};
  constructor(private legionService: LegionService, private router: Router) { }

  ngOnInit(): void {

    this.init();
  }

  init(){
    this.form = new FormGroup({
      nom: new FormControl(''),
      ville: new FormControl(''),
      code: new FormControl('')
    });
  }

  onSubmit(){
    const formData = {
      nom: this.form.get('nom').value,
      ville: this.form.get('ville').value,
      code: this.form.get('code').value
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
