import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { AppComponent }   from './app.component';
import { SignInComponent} from "./signin/signin.component";
import { HttpClientModule }   from '@angular/common/http';
import {Step1Component} from './register/step1.component';
import {Routes, RouterModule} from '@angular/router';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {Step2Component} from "./register/step2.component";
import {RegisterComponent} from "./register/register.component";
import {MainComponent} from "./main_page/main.component";
import {NoAuthGuard} from "./guards/NoAuthGuard";
import { HttpModule } from '@angular/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ProfileComponent} from "./profile/profile.component";
import {KidsComponent} from "./kids/kids.component";
import {SettingsComponent} from "./settings/settings.component";
import {ProfileShowComponent} from "./profile/profile.show/profile.show.component";
import {ProfileEditComponent} from "./profile/profile.edit/profile.edit.component";

//определение маршрутов

const profileRoutes : Routes = [
    {path : '', component: ProfileShowComponent },
    {path : 'edit', component: ProfileEditComponent}
];

const mainRoutes : Routes = [
    {path: "profile", component: ProfileComponent, children: profileRoutes},
    {path: "kids", component: KidsComponent},
    {path: "settings", component: SettingsComponent}
];

const appRoutes: Routes =[
    { path: '', component: SignInComponent},
    { path: 'main', component: MainComponent, canActivate: [NoAuthGuard], children: mainRoutes},
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



@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpClientModule, RouterModule.forRoot(appRoutes),
                    RouterModule.forRoot(loginRoutes), BrowserAnimationsModule, NgbModule.forRoot()/*, RouterModule.forRoot(registerRoutes) */ ],
    declarations: [ AppComponent, RegisterComponent, SignInComponent, Step1Component, Step2Component,
                    MainComponent, ProfileComponent, KidsComponent, SettingsComponent, ProfileShowComponent,
                    ProfileEditComponent  ],
    bootstrap:    [ AppComponent ],
    providers: [NoAuthGuard]
})
export class AppModule { }