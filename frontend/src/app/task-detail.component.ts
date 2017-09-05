import 'rxjs/add/operator/switchMap';
import { Component, OnInit }        from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';

import { Task }         from './task';
import { TaskData } from './task-data';
import { TaskService }  from './task.service';
import { QuestionService } from './question.service';
@Component({
  selector: 'task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: [ './task-detail.component.css' ],
  providers:  [QuestionService]
})
export class TaskDetailComponent implements OnInit {
  task: Task;
  keys: any[];
  values: any[];
  inData: TaskData[];
  outData: TaskData[];
  questions: any[];

  constructor(
    private taskService: TaskService,
    private route: ActivatedRoute,
    private location: Location
    private questionService: QuestionService
  ) { }

  handleNewTask(task) {
    console.log("Handle new task")
    this.task = task;
    this.inData = task.inData;
    this.outData = task.outData;
    // TODO: Use outData, not inData
    this.questions = this.questionService.getQuestionsFromTaskData(task.outData);
    // this.questions = this.questionService.getQuestions();
  }

  ngOnInit(): void {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.taskService.getTask(+params.get('id')))
      .subscribe(task =>{ this.handleNewTask(task) });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.taskService.update(this.task)
      .then(() => this.goBack());
  }
}
