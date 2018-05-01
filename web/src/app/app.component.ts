import { Component } from '@angular/core';
// import {NgForm} from "@angular/forms";
// import {AuthenticationService} from "./_services/authentication.service";
import {SignInComponent} from "./signin.component";

// export class User{
//     username: string;
//     password: string;
// }

@Component({
    selector: 'app',
    // template: `<label>Username:</label>
    //              <input [(ngModel)]="name" placeholder="name">
    //              <h1>Добро пожаловать {{name}}!</h1>`
    template: ` <a routerLink="/register">Registration</a>
        <router-outlet></router-outlet>`

})
export class AppComponent {
    // constructor(private authService: AuthenticationService){}
    // result: boolean = false;
    // done: boolean = false;
    // submit(form: NgForm){
    //    // console.log(form);
    //
    //     this.authService.login(form.value.username, form.value.password)
    //         .subscribe(x => {this.result = x; alert(this.result);})
    //         /*.subscribe(
    //             (   data: boolean) => {this.result=data; this.done=true;},
    //                 error => console.log(error)
    //         )*/;
    // }
}