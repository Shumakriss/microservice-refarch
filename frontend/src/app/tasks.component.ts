import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Task } from './task';
import { TaskService } from './task.service';

@Component({
  selector: 'my-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: [ './tasks.component.css' ]
})
export class TasksComponent implements OnInit {
  tasks: Task[];
  selectedTask: Task;

  constructor(
    private router: Router,
    private taskService: TaskService) { }

  getTasks(): void {
    this.taskService.getTasks().then(tasks => this.tasks = tasks);
  }

  ngOnInit(): void {
    this.getTasks();
  }

  onSelect(task: Task): void {
    this.selectedTask = task;
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectedTask.id]);
  }

}
