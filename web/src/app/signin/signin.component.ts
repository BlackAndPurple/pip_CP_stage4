import { Component } from '@angular/core';
import {NgForm} from "@angular/forms";
import {AuthenticationService} from "../_services/authentication.service";
import { Router } from '@angular/router';
import {NoAuthGuard} from "../guards/NoAuthGuard";

@Component({
    selector: 'signin-form',

    templateUrl: './signin.component.html',
    styleUrls: ['../../../css/login.css'],
    providers: [AuthenticationService]
})
export class SignInComponent {
    constructor(private authService: AuthenticationService, private router: Router){}
    result: boolean = false;
    submit(form: NgForm){

        this.authService.login(form.value.username, form.value.password)
            .subscribe(x => {
                this.result = x;
                //alert(this.result == true);
                if (this.result == true){
                    sessionStorage.setItem("username", form.value.username);
                    this.router.navigateByUrl("/main");
                }else alert("Username or password is incorrect! Please, try one more time");

            })
    }

    signUpClick(){
        this.router.navigateByUrl('/register');
    }
}