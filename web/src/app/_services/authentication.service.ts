import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
//import { RequestOptions } from '@angular/http';

@Injectable()
export class AuthenticationService{

    constructor(private http: HttpClient){ }


    login(username: string, password: string){
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
                //'Authorization': 'my-auth-token'
            })
        };
        const body = JSON.stringify({username: username, password: password});
        let headers = new Headers({ 'Content-Type': 'application/json' });
        /*return*/ this.http.post('./login/user_exists', body, httpOptions).subscribe(data => {
            console.log(data);
        });
    }
    getData(){
        return this.http.get('user.json')
    }
}