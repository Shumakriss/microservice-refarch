import { Component, Input, OnInit }  from '@angular/core';
import { FormGroup }                 from '@angular/forms';

import { QuestionBase }              from './question-base';
import { QuestionControlService }    from './question-control.service';
import { TaskService }               from './task.service';
import { Task }                      from './task'

@Component({
  selector: 'dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: [ './dynamic-form.component.css' ],
  providers: [ QuestionControlService, TaskService]
})
export class DynamicFormComponent implements OnInit {

  @Input() questions: QuestionBase<any>[] = [];
  @Input() task: Task;
  form: FormGroup;
  payLoad = '';
  complete: boolean = false;

  constructor(
    private qcs: QuestionControlService,
    private taskService: TaskService
    ) { }

  ngOnInit() {
    this.form = this.qcs.toFormGroup(this.questions);
    console.log(this.task);
  }

  onSave() {
    console.log("onSave");
    this.taskService.update(this.task, this.form.value);
  }

  onSubmit() {
    console.log("onSubmit");
    this.taskService.complete(this.task, this.form.value);
  }
}
