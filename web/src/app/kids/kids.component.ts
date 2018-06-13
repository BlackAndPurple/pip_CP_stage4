import {Component} from "@angular/core";
import {KidsService} from "../_services/kids.service";
import {ProfileService} from "../_services/profile.service";
import {Router} from "@angular/router";
//import {AuthenticationService} from "../_services/authentication.service";

export class KidCard{
    kidId: number;
    name: string;
    middleName: string;
    surname: string;
    sex: boolean;
}
@Component({
    selector: 'kids',

    templateUrl: './kids.component.html',
    styleUrls: ['../../../css/kids.css'],
    providers: [KidsService]
})
export class KidsComponent {

    kidCards : KidCard[];
    username : string = sessionStorage.getItem("username");
    constructor(private kidsService: KidsService, private router: Router) {}

    ngOnInit(){
        this.kidsService.getKidCards(this.username)
            .subscribe((value : KidCard[]) => {
                this.kidCards = value;
                //alert(this.kidCards[0].name);
            })
    }

    goToKidProfile(kidCard : KidCard){
        //alert("To kid: " + kidCard.name);
        this.router.navigate(['/main/kid', kidCard.kidId]);

    }
}