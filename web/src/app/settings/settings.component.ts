import {Component} from "@angular/core";
import {SettingsService} from "../_services/settings.service";
import {ActivatedRoute} from "@angular/router";
import {KidsService} from "../_services/kids.service";
import {User} from "../register/step2.component";
//import {AuthenticationService} from "../_services/authentication.service";

@Component({
    selector: 'settings',

    templateUrl: './settings.component.html',
    styleUrls: ['../../../css/settings.css'],
    providers: [SettingsService]
})
export class SettingsComponent {

    constructor(private settingsService: SettingsService) {}
    email : string;
    oldPassword : string;
    newPassword : string;
    username : string = sessionStorage.getItem("username");
    user : User;
    ngOnInit(){
        this.settingsService.getEmail(this.username)
            .subscribe((value : User) => {
                this.user = value;
            });
    }

    saveEmail(){

    }

    changePassword(){

    }
}