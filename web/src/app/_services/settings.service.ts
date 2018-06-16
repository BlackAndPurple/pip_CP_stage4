import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {KidCard} from "../kids/kids.component";
import {Person} from "../profile/profile.show/profile.show.component";
import {GroupCard, Med} from "../kids/kid.profile/kid.profile.component";
import {User} from "../register/step2.component";

@Injectable()
export class SettingsService {

    constructor(private http: HttpClient) {}


    /**
     * Allows to get user's email by username
     * @param {string} username
     * @returns {Observable<string>}    email value
     */
    getEmail(username: string) : Observable<User>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({username: username});

        return this.http.post('./settings/get_user', body, httpOptions).map((resp : User) => {return resp});

    }

    /**
     * Allows to post changed email.
     * @param {string} email            Changed email.
     * @returns {Observable<boolean>}   True if saving to db was successful.
     */
    sendEmail(username: string, email : string) : Observable<boolean>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({username: username, email: email});

        return this.http.post('./settings/post_email', body, httpOptions).map((resp : boolean) => {return resp});
    }



}