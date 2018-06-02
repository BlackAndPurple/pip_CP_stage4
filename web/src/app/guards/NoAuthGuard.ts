import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Rx';


//to prevent access of not authentificated users.
@Injectable()
export class NoAuthGuard implements CanActivate {
    constructor(
        // declare variables
    ) {}
    username : string;
    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot,

    ): Observable<boolean> {
        // logic that determines true or false
        this.username = sessionStorage.getItem("username");
        //alert(this.username);
        return Observable.of(!(this.username === null || this.username === ""));
    }
}