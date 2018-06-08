import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Person} from "../profile/profile.component";

@Injectable()
export class ProfileService {

    constructor(private http: HttpClient) {}

    /**
     * Allows to get person data by its username
     * @param {string} username
     * @returns {Observable<Person>}
     */
    getPerson(username : string): Observable<Person>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({username: username});

        return this.http.post('./profile/get_person', body, httpOptions).map((resp : Person) => {return resp});
    }
}