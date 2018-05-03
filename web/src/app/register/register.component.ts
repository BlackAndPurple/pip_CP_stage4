import { Component} from '@angular/core';
import {CalendarModule} from 'primeng/calendar';

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
    templateUrl: './register.component.html',
    styleUrls: ['../../../css/login.css'],
})
export class RegisterComponent {
    person: Person = new Person();

}