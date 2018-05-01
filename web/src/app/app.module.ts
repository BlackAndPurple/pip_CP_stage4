import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }   from './app.component';
import { SignInComponent} from "./signin.component";
import { HttpClientModule }   from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { RegisterComponent }   from './register.component';
import {Routes, RouterModule} from '@angular/router';


//определение маршрутов
const appRoutes: Routes =[
    { path: '', component: SignInComponent},
    { path: 'signin', component: SignInComponent},
    { path: 'register', component: RegisterComponent},
    { path: '**', component: RegisterComponent }
];


@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpClientModule, HttpModule, RouterModule.forRoot(appRoutes) ],
    declarations: [ AppComponent, RegisterComponent, SignInComponent  ],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }