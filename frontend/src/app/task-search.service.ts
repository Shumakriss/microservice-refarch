import { Injectable } from '@angular/core';
import { Http }       from '@angular/http';

import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Task }           from './task';

@Injectable()
export class TaskSearchService {

 	private headers = new Headers({'Content-Type': 'application/json'});

	constructor(private http: Http) {}

	search(term: string): Observable<Task[]> {
		return this.http.get(`/api/bpm/task?term=${term}`)
			.toPromise()
			.then(response => response.json().tasks as Task[])
			.catch(this.handleError);
	}

	private handleError(error: any): Promise<any> {
		console.error('An error occurred', error); // for demo purposes only
		return Promise.reject(error.message || error);
	}
}