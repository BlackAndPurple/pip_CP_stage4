import {Component} from "@angular/core";
import {KidsService} from "../../_services/kids.service";
import {ProfileService} from "../../_services/profile.service";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {Person} from "../../profile/profile.show/profile.show.component";
import {KidCard} from "../kids.component";
//import {AuthenticationService} from "../_services/authentication.service";

export class GroupCard{
    groupName : string;
    groupType: string;
    since: string;
    till: string;
}
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
    gender : string;
    groupCards: GroupCard[];

    constructor(private kidsService: KidsService, private activateRoute: ActivatedRoute) {
        this.subscription = activateRoute.params.subscribe(params=>this.kidId = params['kidId']);
    }

    ngOnInit(){
        this.kidsService.getKidPersonality(this.kidId)
            .subscribe((value : Person) => {
                this.kidPerson = value;
                this.gender = this.kidPerson.sex ? "Male" : "Female";
                //alert(this.kidCards[0].name);
            });

        this.kidsService.getGroupCards(this.kidId)
            .subscribe((value : GroupCard[]) =>
            {
                this.groupCards = value;
            })
    }


}