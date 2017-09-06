import { Injectable }       from '@angular/core';

import { DropdownQuestion } from './question-dropdown';
import { QuestionBase }     from './question-base';
import { TextboxQuestion }  from './question-textbox';

import { Task } From './task'
import { TaskData } From './task-data'

@Injectable()
export class QuestionService {

  private questions: QuestionBase<any>[] = []
 
  getQuestionsFromTaskData(taskData: TaskData[]) {
    console.log("Get questions from task");

    taskData.forEach(datum => {
      console.log(datum);
      var question;
      switch(datum.type) {
        case "java.lang.String":
          question = this.getQuestionFromString(datum);
          break;
        case "String":
          question = this.getQuestionFromString(datum);
          break;
        case "Object":
          question = this.getQuestionFromObject(datum);
          break;
        case "org.jbpm.examples.data.User":
          question = this.getQuestionFromUser(datum);
          break;
        default:
          console.log("No question for data type");
          console.log(datum.type);
      }

      this.questions.push(question)
    });

    console.log(this.questions)

    return this.questions;
  }

  getQuestionFromString(datum : TaskData) {
    return new TextboxQuestion({
            key: datum.name,
            label: datum.name,
            value: datum.value,
            required: true,
            order: 1
          });
  }

  getQuestionFromObject(datum : TaskData) {
    return new TextboxQuestion({
            key: datum.name,
            label: datum.name,
            value: datum.value,
            required: true,
            order: 1
          });
  }

  getQuestionFromUser(datum : TaskData) {
    console.log("Getting question from org.jbpm.examples.data.User");
    console.log(datum);
    return new TextboxQuestion({
            key: datum.name,
            label: datum.name,
            value: datum.value,
            required: true,
            order: 1
          });
  }  

  //   getQuestions() {

  //   let questions: QuestionBase<any>[] = [

  //     new DropdownQuestion({
  //       key: 'brave',
  //       label: 'Bravery Rating',
  //       options: [
  //         {key: 'solid',  value: 'Solid'},
  //         {key: 'great',  value: 'Great'},
  //         {key: 'good',   value: 'Good'},
  //         {key: 'unproven', value: 'Unproven'}
  //       ],
  //       order: 3
  //     }),

  //     new TextboxQuestion({
  //       key: 'firstName',
  //       label: 'First name',
  //       value: 'Bombasto',
  //       required: true,
  //       order: 1
  //     }),

  //     new TextboxQuestion({
  //       key: 'emailAddress',
  //       label: 'Email',
  //       type: 'email',
  //       order: 2
  //     })
  //   ];

  //   return questions.sort((a, b) => a.order - b.order);
  // }

}
