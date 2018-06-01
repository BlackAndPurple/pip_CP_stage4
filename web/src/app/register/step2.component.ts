import { Component} from '@angular/core';
import {RegistrationService} from "../_services/registration.service";
import {Subscription} from "rxjs/Subscription";

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

    constructor(private regService: RegistrationService){
    }
     submit(user : User){

         alert(this.user.personId+" " + this.user.username);

    //     this.regService.getPersonId(person)
    //         .subscribe(id_ => {this.id = id_; alert(id_);})
     }

    // submit(form: NgForm){
    //     alert("alert!");
    // }

    // onSubmit(f: NgForm) {
    //     alert("alert!");
    // }
}