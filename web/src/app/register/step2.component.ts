import { Component} from '@angular/core';
import {RegistrationService} from "../_services/registration.service";
import {Router} from "@angular/router";

export class User{
    username: string;
    password: string;
    personId: string;
    email: string;
    constructor(personId: string) {
        this.personId = personId;
    }
}

@Component({
    selector: 'register2-form',
    templateUrl: './step2.component.html',
    styleUrls: ['../../../css/login.css'],
    providers: [RegistrationService]
})
export class Step2Component {

    personId: string = sessionStorage.getItem("personId");
    user: User = new User(this.personId);

    constructor(private regService: RegistrationService, private router: Router){
    }
     submit(user : User){

         this.regService.addUser(this.user);
         alert("You've successfully registered! Sign in to continue... ");
         this.router.navigateByUrl('/');

     }

}