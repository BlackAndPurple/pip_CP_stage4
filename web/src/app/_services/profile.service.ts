import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Contacts, Person} from "../profile/profile.show/profile.show.component";
//import {Contacts, Person} from "../profile/profile.component";

@Injectable()
export class ProfileService {

    constructor(private http: HttpClient) {}

    /**
     * Allows to get person data by username
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

    /**
     * Allows to get parent's contacts by username
     * @param {string} username
     * @returns {Observable<Contacts>}
     */
    getContacts(username : string) : Observable<Contacts>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({username: username});

        return this.http.post('./profile/get_contacts', body, httpOptions).map((resp : Contacts) => {return resp});
    }

    updatePerson(username: string, person : Person): Observable<boolean>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };

        const body = JSON.stringify({username: username,
                                            name: person.name,
                                            middleName: person.middleName,
                                            surname: person.surname,
                                            sex: person.sex,
                                            dateOfBirth: person.dateOfBirth});

        return this.http.post('./profile/update_person', body, httpOptions)
            .map((resp : boolean) => {return resp});
    }

    postContacts(username: string, contacts: Contacts): Observable<boolean>{

        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({username: username,
                                            homeAddress: contacts.homeAddress,
                                            job: contacts.job,
                                            jobPhoneNumber: contacts.jobPhoneNumber,
                                            cellphoneNumber: contacts.cellphoneNumber});

        return this.http.post('./profile/post_contacts', body, httpOptions)
            .map((resp : boolean) => {return resp});

    }

}