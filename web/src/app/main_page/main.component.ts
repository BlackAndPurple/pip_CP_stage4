import {Router} from "@angular/router";
import {AuthenticationService} from "../_services/authentication.service";
import {Component} from "@angular/core";
import {NgForm} from "@angular/forms";

@Component({
    selector: 'main',

    templateUrl: './main.component.html',
    styleUrls: ['../../../css/login.css'] //??
    //providers: [AuthenticationService]
})
export class MainComponent {
    constructor(private router: Router){}

}