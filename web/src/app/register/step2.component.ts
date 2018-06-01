import { Component} from '@angular/core';
import {RegistrationService} from "../_services/registration.service";
import {Subscription} from "rxjs/Subscription";

export class User{
    username: string;
    password: string;
    personId: number;
    email: string;
}

@Component({
    selector: 'register2-form',
    templateUrl: './step2.component.html',
    styleUrls: ['../../../css/login.css'],
    providers: [RegistrationService]
})
export class Step2Component {
    dataPassed: number;
    subscription: Subscription;
    constructor(private regService: RegistrationService){
        this.subscription = this.regService.getData().subscribe(x => {this.dataPassed = x; alert("data passed: "+this.dataPassed)});
    }
    user: User = new User();
    //id: number = 0;

     submit(user : User){
         alert("alert!");
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