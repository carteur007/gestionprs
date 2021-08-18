import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './user/home/home.component';
import { AddUserComponent } from './user/add-user/add-user.component';
import { AddUsersComponent } from './user/add-users/add-users.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {NgxPaginationModule} from "ngx-pagination";
import {Ng2SearchPipe, Ng2SearchPipeModule} from "ng2-search-filter";
import { DetailUserComponent } from './user/detail-user/detail-user.component';
import { LegionHomeComponent } from './legion/legion-home/legion-home.component';
import { LegionAddComponent } from './legion/legion-add/legion-add.component';

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
    LegionHomeComponent,
    LegionAddComponent
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
