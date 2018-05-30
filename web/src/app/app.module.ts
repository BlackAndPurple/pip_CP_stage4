import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }   from './app.component';
import { SignInComponent} from "./signin/signin.component";
import { HttpClientModule }   from '@angular/common/http';
//import { HttpModule } from '@angular/http';
import { RegisterComponent }   from './register/register.component';
import {Routes, RouterModule} from '@angular/router';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {Register2Component} from "./register/register2.component";


//определение маршрутов
const appRoutes: Routes =[
    { path: '', component: SignInComponent},
    //{ path: 'signin', component: SignInComponent},
    //{ path: 'register', component: RegisterComponent},
    //{ path: '**', component: RegisterComponent }
];

const loginRoutes : Routes = [
    {path: "next", component: Register2Component},
    {path: "register", component: RegisterComponent,
        // children: [
        //     {path: "next", component: Register2Component}]
    }

];

// const registerRoutes : Routes = [ //probably should be changed to child routing
//     {path: "registernext", component: Register2Component}
// ];
@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpClientModule, RouterModule.forRoot(appRoutes),
                    RouterModule.forRoot(loginRoutes), BrowserAnimationsModule/*, RouterModule.forChild(loginRoutes)/*, RouterModule.forRoot(registerRoutes) */ ],
    declarations: [ AppComponent, RegisterComponent, SignInComponent, Register2Component  ],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }