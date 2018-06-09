import {Component, EventEmitter, Output} from '@angular/core';
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
    id: number = -1;

    submit(person : Person){
        this.regService.getPersonId(person)
            .subscribe(id_ => {

                this.id = id_ > 0 ? id_ : -1;
                if (this.id != -1){
                    this.regService.parentExists(this.id)
                        .subscribe((result : boolean) => {
                            //alert("parent exists: " + result);
                            if (result){
                                sessionStorage.setItem("personId", this.id.toString());
                                this.router.navigateByUrl('/register/step2');
                            }else alert("This person is not a parent!")
                        });

                }else alert("Data is incorrect!");});


    }


}