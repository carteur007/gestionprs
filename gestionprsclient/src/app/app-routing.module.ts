import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './user/home/home.component';
import { AddUserComponent } from './user/add-user/add-user.component';
import { AddUsersComponent } from './user/add-users/add-users.component';
import { DetailUserComponent } from './user/detail-user/detail-user.component';
import { LegionHomeComponent } from './legion/legion-home/legion-home.component';
import { LegionAddComponent } from './legion/legion-add/legion-add.component';

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
  {
    path:'users/detail/:id', component:DetailUserComponent
  },
  {
    path:'legions/legions', component:LegionHomeComponent
  },
  {
    path:'legions/add', component:LegionAddComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
