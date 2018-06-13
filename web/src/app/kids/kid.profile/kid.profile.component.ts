import {Component} from "@angular/core";
import {KidsService} from "../../_services/kids.service";
import {ProfileService} from "../../_services/profile.service";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {Person} from "../../profile/profile.show/profile.show.component";
import {KidCard} from "../kids.component";
//import {AuthenticationService} from "../_services/authentication.service";


@Component({
    selector: 'kid-profile',

    templateUrl: './kid.profile.component.html',
    styleUrls: ['../../../../css/kids.css'],
    providers: [KidsService]
})
export class KidProfileComponent {

    username : string = sessionStorage.getItem("username");
    private subscription: Subscription;
    kidId: number;
    kidPerson : Person;
    constructor(private kidsService: KidsService, private activateRoute: ActivatedRoute) {
        //this.kidId = activateRoute.snapshot.params['kidId'];
        this.subscription = activateRoute.params.subscribe(params=>this.kidId = params['kidId']);
        //alert(this.kidId);
    }

    ngOnInit(){
        this.kidsService.getKidPersonality(this.kidId)
            .subscribe((value : Person) => {
                this.kidPerson = value;
                //alert(this.kidCards[0].name);
            })
    }


}