import {Router} from "@angular/router";
import {AuthenticationService} from "../_services/authentication.service";
import {Component} from "@angular/core";
import {NgForm} from "@angular/forms";
import {NoAuthGuard} from "../guards/NoAuthGuard";
import {NgbCollapse} from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'main',

    templateUrl: './main.component.html',
    styleUrls: ['../../../css/main.css'] //??

})
export class MainComponent {
    isCollapsed: boolean;
    constructor(private router: Router){
        this.isCollapsed = false;
    }

}