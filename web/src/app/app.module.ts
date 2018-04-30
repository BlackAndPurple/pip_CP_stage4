import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }   from './app.component';
import { HttpClientModule }   from '@angular/common/http';
import { RegisterComponent }   from './register.component';
import {Routes, RouterModule} from '@angular/router';


// определение маршрутов
// const appRoutes: Routes =[
//     //{ path: '', component: HomeComponent},
//     { path: 'register', component: RegisterComponent},
//     //{ path: '**', component: NotFoundComponent }
// ];


@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpClientModule/*,  RouterModule.forRoot(appRoutes)*/ ],
    declarations: [ AppComponent, RegisterComponent ],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }