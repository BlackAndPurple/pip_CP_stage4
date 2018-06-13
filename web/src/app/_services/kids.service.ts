import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {KidCard} from "../kids/kids.component";
import {Person} from "../profile/profile.show/profile.show.component";

@Injectable()
export class KidsService {

    constructor(private http: HttpClient) {}

    /**
     * Allows to get kid data to be displayed on kids page as a group of cards
     * @param {string} username
     * @returns {Observable<>}   all kids data
     */
    getKidCards(username: string) : Observable<KidCard[]>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({username: username});

        return this.http.post('./kids/get_kid_card', body, httpOptions).map((resp : KidCard[]) => {return resp});

    }

    /**
     * Allows to get kid's personal data like name and etc.
     * @param {number} kidId            kid's id
     * @returns {Observable<Person>}
     */
    getKidPersonality(kidId : number) : Observable<Person>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({kidId: kidId});

        return this.http.post('./kids/get_kid_person', body, httpOptions).map((resp : Person) => {return resp});
    }


}