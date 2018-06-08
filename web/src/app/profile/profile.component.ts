import {Component} from "@angular/core";
import {RegistrationService} from "../_services/registration.service";
import {ProfileService} from "../_services/profile.service";
//import {AuthenticationService} from "../_services/authentication.service";

export class Person{
    name: string;
    middle_name: string;
    surname: string;
    gender: boolean;
    date_of_birth: string;

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
    person : Person;

    imageSrc = require('../../../static/user.png');

    ngOnInit(){
        this.profileService.getPerson(this.username)
            .subscribe((person : Person) => {this.person = person;})
    }

}