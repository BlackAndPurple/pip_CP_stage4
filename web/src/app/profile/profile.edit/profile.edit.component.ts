import {Component} from "@angular/core";
import {ProfileService} from "../../_services/profile.service";
//import {AuthenticationService} from "../_services/authentication.service";

export class Person{
    name: string;
    middleName: string;
    surname: string;
    sex: boolean;
    dateOfBirth: string;
}

export class Contacts{
    homeAddress : string;
    job : string;
    jobPhoneNumber : string;
    cellphoneNumber : string;
    //date of creating
}

@Component({
    selector: 'profile-show',

    templateUrl: './profile.edit.component.html',
    styleUrls: ['../../../../css/profile.css'],
    providers: [ProfileService]
})
export class ProfileEditComponent {
    constructor(private profileService: ProfileService) {
    }
    username : string = sessionStorage.getItem("username");
    person : Person = new Person;
    contacts : Contacts = new Contacts;
    //gender : string;

    date: Date;

    //imageSrc = require('../../images/user.png');

    ngOnInit(){

        //getting personal data
        this.profileService.getPerson(this.username)
            .subscribe((person : Person) => {
                this.person = person;

                //this.gender = this.person.sex ? "Male" : "Female";
            });
        //
        //
        //getting parent contacts
        this.profileService.getContacts(this.username)
            .subscribe((contacts : Contacts) => {
                this.contacts = contacts;
            })
    }

    submit(){
        alert("form submitted");
        //post person data
        this.profileService.updatePerson(this.username, this.person);

        //post contacts data
        this.profileService.postContacts(this.username, this.contacts);
    }


}