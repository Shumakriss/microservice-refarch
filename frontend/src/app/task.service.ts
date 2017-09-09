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
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Task)
      .catch(this.handleError);
  }

  update(task: Task, processVariables: any): Promise<Task> {
    const url = `${this.tasksUrl}/${task.id}`;
    task = this.mapVariablesOntoTask(task, processVariables);
    return this.http
      .put(url, JSON.stringify(task), {headers: this.headers})
      .toPromise()
      .then(() => task)
      .catch(this.handleError);
  }

  complete(task: Task, processVariables: any): Promise<Task> {
    const url = `${this.tasksUrl}/${task.id}/states/completed`;
    task = this.mapVariablesOntoTask(task, processVariables);
    return this.http
      .put(url, JSON.stringify(task), {headers: this.headers})
      .toPromise()
      .then(() => task)
      .catch(this.handleError);
  }

  mapVariablesOntoTask(task: Task, processVariables: any){
    var outData = task.outData as TaskData[];
    Object.keys(processVariables).map( (name, discard) => {
      console.log(name);
      outData.map( (datum,discard) => {
        if(datum.name == name) {
            datum.value = processVariables[name];
        }
      });
    });
    console.log(task);
    return task;
  }

}
