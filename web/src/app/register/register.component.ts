import { Component } from '@angular/core';
import {RegistrationService} from "../_services/registration.service";
import {Subscription} from "rxjs/Subscription";
//import {SignInComponent} from "./signin.component";


@Component({
    selector: 'register',
    template: `<router-outlet></router-outlet>`,
    providers: [RegistrationService]

})
export class RegisterComponent {
    // dataPassed: number;
    // subscription: Subscription;
    // // onNotify(personId:number):void {
    // //     alert("recieved: " + personId);
    // // }
    // constructor(private regService: RegistrationService){
    //     this.subscription = this.regService.getData().subscribe(x => {this.dataPassed = x; alert("data passed: "+this.dataPassed)});
    // }

}