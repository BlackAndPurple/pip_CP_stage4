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


//определение маршрутов
const appRoutes: Routes =[
    { path: '', component: SignInComponent},
    //{ path: 'signin', component: SignInComponent},
    //{ path: 'register', component: RegisterComponent},
    //{ path: '**', component: RegisterComponent }
];

const loginRoutes : Routes = [
    {path: "register", component: RegisterComponent}
];

@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpClientModule, RouterModule.forRoot(appRoutes),
                    RouterModule.forRoot(loginRoutes), BrowserAnimationsModule  ],
    declarations: [ AppComponent, RegisterComponent, SignInComponent  ],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }