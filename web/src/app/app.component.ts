import { Component } from '@angular/core';
import {NoAuthGuard} from "./guards/NoAuthGuard";
//import {SignInComponent} from "./signin.component";


@Component({
    selector: 'app',
    template: `<router-outlet></router-outlet>`

})
export class AppComponent {
}