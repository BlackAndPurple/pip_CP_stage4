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
    templateUrl: './register2.component.html',
    styleUrls: ['../../../css/login.css'],
    providers: [RegistrationService]
})
export class Register2Component {
    constructor(private regService: RegistrationService){}
    person: User = new User();
    //id: number = 0;

    // submit(person : Person){
    //     //alert("alert!");
    //     this.regService.getPersonId(person)
    //         .subscribe(id_ => {this.id = id_; alert(id_);})
    // }

    // submit(form: NgForm){
    //     alert("alert!");
    // }

    // onSubmit(f: NgForm) {
    //     alert("alert!");
    // }
}