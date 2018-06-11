import {Component} from "@angular/core";
// import {ProfileService} from "../_services/profile.service";


@Component({
    selector: 'profile',

    templateUrl: './profile.component.html',
    styleUrls: ['../../../css/profile.css'],
    //providers: [ProfileService]
})
export class ProfileComponent {
    // constructor(private profileService: ProfileService) {
    // }
    // username : string = sessionStorage.getItem("username");
    // person : Person = new Person;
    // contacts : Contacts = new Contacts;
    // gender : string;
    //
    // date: Date;
    //
    // imageSrc = require('../../images/user.png');
    //
    // ngOnInit(){
    //
    //     //getting personal data
    //     this.profileService.getPerson(this.username)
    //         .subscribe((person : Person) => {
    //             this.person = person;
    //             this.gender = this.person.sex ? "Male" : "Female";
    //         });
    //
    //
    //     //getting parent contacts
    //     this.profileService.getContacts(this.username)
    //         .subscribe((contacts : Contacts) => {
    //             this.contacts = contacts;
    //         })
    // }
    //


}