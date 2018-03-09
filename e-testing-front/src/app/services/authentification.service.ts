import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { User } from '../models/user';
import { UserService } from './user.service';

@Injectable()
export class AuthentificationService {
    public token: string;
    public server: string;
    public user: User;
    public isLogged; boolean;

    constructor(private http: Http) {
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
        this.server = 'http://localhost:8080';
    }

    login(mail: string, password: string): Observable<Boolean> {
        const body = JSON.stringify({ mail: mail, password: password });
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });

        return this.http.post(this.server + '/api-token', body, options)
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                const token = response.json().token;
                if (token) {
                    // set token property
                    this.token = token;
                    this.user = new User({ mail: mail, token: token });
                    this.isLogged = true;

                    // store username and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(this.user));

                    // return true to indicate successful login
                    return true;
                }
            })
            .catch((error: any) => {
                return Observable.throw(error);
            });
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.token = null;
        this.isLogged = false;
        localStorage.removeItem('currentUser');
    }

}