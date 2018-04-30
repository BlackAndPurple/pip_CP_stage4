import { Component } from '@angular/core';
import {NgForm} from "@angular/forms";
import {AuthenticationService} from "./_services/authentication.service";

export class User{
    username: string;
    password: string;
}

@Component({
    selector: 'login-form',
    // template: `<label>Username:</label>
    //              <input [(ngModel)]="name" placeholder="name">
    //              <h1>Добро пожаловать {{name}}!</h1>`
    template: ` <form #signInForm="ngForm" novalidate>
                    <div class="form-group">
                        <label>Username</label>
                        <input class="form-control" name="username" [(ngModel)]="username"  required />
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input class="form-control" name="password" [(ngModel)]="password"  required />
                    </div>
                    <div class="form-group">
                        <button [disabled]="signInForm.invalid"
                                class="btn btn-default" (click)="submit(signInForm)">Submit</button>
                    </div>
                </form>
                <div>Имя: {{signInForm.value.username}}</div>
                <div>Email: {{signInForm.value.password}}</div>`,
    providers: [AuthenticationService]
})
export class AppComponent {
    constructor(private authService: AuthenticationService){}
    result: boolean = false;
    done: boolean = false;
    submit(form: NgForm){
       // console.log(form);

        this.authService.login(form.value.username, form.value.password)
            .subscribe(x => {this.result = x; alert(x);})
            /*.subscribe(
                (   data: boolean) => {this.result=data; this.done=true;},
                    error => console.log(error)
            )*/;
    }
}