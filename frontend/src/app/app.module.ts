import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule }    from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard.component';
import { TaskDetailComponent }  from './task-detail.component';
import { TasksComponent }      from './tasks.component';
import { TaskService }          from './task.service';
import { TaskSearchComponent }  from './task-search.component';

import { AppRoutingModule }     from './app-routing.module';

import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService }  from './in-memory-data.service;

import { DynamicFormComponent }         from './dynamic-form.component';
import { DynamicFormQuestionComponent } from './dynamic-form-question.component';
import { ReplaceUnderscores } from './replace-underscores';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpModule,
    ReactiveFormsModule
    // InMemoryWebApiModule.forRoot(InMemoryDataService),
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    TaskDetailComponent,
    TasksComponent,
    TaskSearchComponent,
    DynamicFormComponent,
    DynamicFormQuestionComponent,
    ReplaceUnderscores
  ],
  providers: [ TaskService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
