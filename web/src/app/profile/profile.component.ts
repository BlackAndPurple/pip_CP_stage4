import {Component} from "@angular/core";
import {ProfileService} from "../_services/profile.service";
//import {AuthenticationService} from "../_services/authentication.service";

export class Person{
    name: string;
    middleName: string;
    surname: string;
    sex: boolean;
    dateOfBirth: string;

}

@Component({
    selector: 'profile',

    templateUrl: './profile.component.html',
    styleUrls: ['../../../css/profile.css'],
    providers: [ProfileService]
})
export class ProfileComponent {
    constructor(private profileService: ProfileService) {
    }
    username : string = sessionStorage.getItem("username");
    person : Person = new Person;
    gender : string;

    imageSrc = require('../../../static/user.png');

    ngOnInit(){
        this.profileService.getPerson(this.username)
            .subscribe((person : Person) => {
                this.person = person;
                this.gender = this.person.sex ? "Male" : "Female";
            })
    }

}