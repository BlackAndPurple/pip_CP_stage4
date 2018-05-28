import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs/Observable";
import { HttpModule } from '@angular/http';
//import { RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService{

    constructor(private http: HttpClient){ }


    login(username: string, password: string): Observable<boolean>{
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
                //'Authorization': 'my-auth-token'
            })
        };
        const body = JSON.stringify({username: username, password: password});
        //let headers = new Headers({ 'Content-Type': 'application/json' });
        return this.http.post('./login/user_exists', body, httpOptions).map(resp => {return resp.toString() == "true"});/*.subscribe(data => {
            console.log(data);
        })*/;
    }

}