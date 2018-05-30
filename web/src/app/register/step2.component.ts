import { Component} from '@angular/core';
import {RegistrationService} from "../_services/registration.service";

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
    constructor(private regService: RegistrationService){}
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