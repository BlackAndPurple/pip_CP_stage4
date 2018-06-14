import {Component} from "@angular/core";
//import {AuthenticationService} from "../_services/authentication.service";

@Component({
    selector: 'settings',

    templateUrl: './settings.component.html',
    styleUrls: ['../../../css/settings.css'],
    //providers: [AuthenticationService]
})
export class SettingsComponent {

    email : string;

    saveEmail(){

    }
}