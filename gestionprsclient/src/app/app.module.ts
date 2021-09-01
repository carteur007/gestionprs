import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './user/home/home.component';
import { AddUserComponent } from './user/add-user/add-user.component';
import { AddUsersComponent } from './user/add-users/add-users.component';
import {RouterModule} from "@angular/router";
import {NgxPaginationModule} from "ngx-pagination";
import {Ng2SearchPipe, Ng2SearchPipeModule} from "ng2-search-filter";
import { DetailUserComponent } from './user/detail-user/detail-user.component';
import { LegionAddComponent } from './legion/legion-add/legion-add.component';
import { LegionHomeComponent } from './legion/legion-home/legion-home.component';
import { LegionUpdateComponent } from './legion/legion-update/legion-update.component';
import { GroupementHomeComponent } from './groupement/home/groupement-home.component';
import { GroupementAddComponent } from './groupement/groupement-add/groupement-add.component';
import { GroupementUpdateComponent } from './groupement/groupement-update/groupement-update.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    AddUserComponent,
    AddUsersComponent,
    DetailUserComponent,
    LegionAddComponent,
    LegionHomeComponent,
    LegionUpdateComponent,
    GroupementHomeComponent,
    GroupementAddComponent,
    GroupementUpdateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    Ng2SearchPipeModule,
    RouterModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
////  