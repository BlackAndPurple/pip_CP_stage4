import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';
import {Person} from "../register/step1.component";
import {Subject} from "rxjs/Subject";
import {User} from "../register/step2.component";

@Injectable()
export class RegistrationService{

    constructor(private http: HttpClient){ };

    getPersonId(person : Person) : Observable<number>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({name: person.name, middle_name: person.middle_name,
                                            surname: person.surname, sex: person.gender,
                                                    date_of_birth: person.date_of_birth});
        return this.http.post('./login/person_exists', body, httpOptions).map((resp : number) => { return resp}) ;
    }

    parentExists(personId : number): Observable<boolean>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({personId: personId});
        return this.http.post('./login/parent_exists', body, httpOptions).map((resp : boolean) => {return resp});
    }

    //add new user record to db.
    addUser(user : User): Observable<any>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({username: user.username, personId: user.personId,
                                            password: user.password, email: user.email});
        return this.http.post('./login/add_user', body, httpOptions).map(resp => {return resp});
    }

    //check if username already exists in db.
    usernameExists(username : string) : Observable<any>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({username: username});
        return this.http.post('./login/username_exists', body, httpOptions).map(resp => {return resp});
    }


}