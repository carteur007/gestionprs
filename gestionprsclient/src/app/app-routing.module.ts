import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './user/home/home.component';
import { AddUserComponent } from './user/add-user/add-user.component';
import { AddUsersComponent } from './user/add-users/add-users.component';
import { DetailUserComponent } from './user/detail-user/detail-user.component';
import { LegionHomeComponent } from './legion/legion-home/legion-home.component';
import { LegionAddComponent } from './legion/legion-add/legion-add.component';
import { LegionUpdateComponent } from './legion/legion-update/legion-update.component';
import { GroupementHomeComponent } from './groupement/home/groupement-home.component';
import { GroupementAddComponent } from './groupement/groupement-add/groupement-add.component';

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
    path:'legions/add/legion', component:LegionAddComponent
  },
  {
    path:'legions/add/legion', component:LegionAddComponent
  },
  {
    path:'legions/update/legion/:id', component:LegionUpdateComponent
  },
  {
    path:'groupements/groupements/:id', component: GroupementHomeComponent
  },
  {
    path:'groupements/add/groupement/:id', component: GroupementAddComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
