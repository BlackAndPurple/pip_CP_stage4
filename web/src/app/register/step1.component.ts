import { Component} from '@angular/core';
import {RegistrationService} from "../_services/registration.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

export class Person{
    //constructor(gender = "male");
    name: string;
    middle_name: string;
    surname: string;
    gender: string = "male";
    date_of_birth: Date;
}

@Component({
    selector: 'register-form',
    templateUrl: './step1.component.html',
    styleUrls: ['../../../css/login.css'],
    providers: [RegistrationService]
})
export class Step1Component {
    constructor(private regService: RegistrationService, private router: Router){}
    person: Person = new Person();
    id: number = 0;

    submit(person : Person){
        //alert("alert!");
        this.regService.getPersonId(person)
            .subscribe(id_ => {this.id = id_; alert(id_);});
        if (this.id != null)
            this.router.navigateByUrl('/register/step2');
    }

    // submit(form: NgForm){
    //     alert("alert!");
    // }

    // onSubmit(f: NgForm) {
    //     alert("alert!");
    // }

}