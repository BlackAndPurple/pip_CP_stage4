import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';
import {Person} from "../register/register.component";

@Injectable()
export class RegistrationService{

    constructor(private http: HttpClient){ }


    getPersonId(person : Person) : Observable<any>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
            })
        };
        const body = JSON.stringify({name: person.name, middle_name: person.middle_name,
                                            surname: person.surname, sex: person.gender,
                                                    date_of_birth: person.date_of_birth});
        return this.http.post('./login/person_exists', body, httpOptions).map(resp => {return resp}) ;
    }

}