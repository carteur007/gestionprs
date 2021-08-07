import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./user/home/home.component";
import {AddUserComponent} from "./user/add-user/add-user.component";
import {AddUsersComponent} from "./user/add-users/add-users.component";

const routes: Routes = [
  {
    path:'users/users', component:HomeComponent
  },
  {
    path:'users/add/user', component:AddUserComponent
  },
  {
    path:'users/add/users', component:AddUsersComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
