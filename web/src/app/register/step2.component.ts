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
    success : boolean;
    usernameExists : boolean;

    constructor(private regService: RegistrationService, private router: Router){}
     submit(user : User){

        this.regService.usernameExists(this.user.username).subscribe(usernameExists => {
            this.usernameExists = usernameExists;
            if (!usernameExists)
                this.regService.addUser(this.user)
                    .subscribe(result => {
                        this.success = result;
                        if (this.success){
                            sessionStorage.removeItem("personId");
                            alert("You've successfully registered! Log in to continue... ");
                            this.router.navigateByUrl('/');
                        }else alert("Error while registering! Try one more time...");
                    });
            else alert("Such username already exists! Choose another one")
        })

     }

}