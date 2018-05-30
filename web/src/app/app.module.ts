import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }   from './app.component';
import { SignInComponent} from "./signin/signin.component";
import { HttpClientModule }   from '@angular/common/http';
//import { HttpModule } from '@angular/http';
import {Step1Component} from './register/step1.component';
import {Routes, RouterModule} from '@angular/router';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {Step2Component} from "./register/step2.component";
import {RegisterComponent} from "./register/register.component";


//определение маршрутов
const appRoutes: Routes =[
    { path: '', component: SignInComponent},
    //{ path: 'signin', component: SignInComponent},
    //{ path: 'register', component: RegisterComponent},
    //{ path: '**', component: RegisterComponent }
];

const registerRoutes : Routes = [
    { path: '', redirectTo: 'step1', pathMatch: 'full' },
    {path: "step1", component: Step1Component},
    {path: "step2", component: Step2Component},
    /*{path: '', component: Step1Component}*/
];

const loginRoutes : Routes = [
    {path: "register", component: RegisterComponent, children: registerRoutes}
];


// const registerRoutes : Routes = [ //probably should be changed to child routing
//     {path: "registernext", component: Register2Component}
// ];
@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpClientModule, RouterModule.forRoot(appRoutes),
                    RouterModule.forRoot(loginRoutes), BrowserAnimationsModule/*, RouterModule.forRoot(registerRoutes) */ ],
    declarations: [ AppComponent, RegisterComponent, SignInComponent, Step1Component, Step2Component  ],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }