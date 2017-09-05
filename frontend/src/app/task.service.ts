import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Task } from './task';

@Injectable()
export class TaskService {

  private tasksUrl = '/api/bpm/task';  // URL to web api
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  getTasks(): Promise<Task[]> {
    return this.http.get(this.tasksUrl)
               .toPromise()
               .then(response => response.json().tasks as Task[])
               .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  getTask(id: number): Promise<Task> {
    const url = `${this.tasksUrl}/${id}`;
    // const config = {params: {containerId: "Evaluation", processId:"evaluation"}};
    // return this.http.get(url, config)
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Task)
      // .then(response => console.log(response.json()))
      .catch(this.handleError);
  }

  // TODO: Finish
  update(task: Task): Promise<Task> {
    const url = `${this.tasksUrl}/${task.id}`;

    return this.http
      .put(url, JSON.stringify(task), {headers: this.headers})
      .toPromise()
      .then(() => task)
      .catch(this.handleError);
  }

  // TODO: Finish
  create(name: string): Promise<Task> {
    return this.http
      .post(this.tasksUrl, JSON.stringify({name: name}), {headers: this.headers})
      .toPromise()
      .then(res => res.json().data as Task)
      .catch(this.handleError);
  }

  // TODO: Finish
  delete(id: number): Promise<void> {
    const url = `${this.tasksUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }
}
