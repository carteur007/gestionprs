import { UserComponent } from './user/user.component';
import { UserAddComponent } from './user/user-add/user-add.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', redirectTo: '/app-all', pathMatch: 'full'},
  {path: 'app-user', component: UserComponent},
  {path: 'app-user-add', component: UserAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
