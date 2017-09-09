import { Injectable }       from '@angular/core';

import { DropdownQuestion } from './question-dropdown';
import { QuestionBase }     from './question-base';
import { TextboxQuestion }  from './question-textbox';
import { CheckboxQuestion }  from './question-checkbox';
import { RadioButtonQuestion }  from './question-radio';

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
        case "TextboxQuestion":
          question = new TextboxQuestion({
            key: datum.name,
            label: datum.name,
            value: datum.value,
            required: true,
            order: 2
          });
          break;
        case "DropdownQuestion":
          question = new DropdownQuestion({
            key: datum.name,
            label: datum.name,
            value: datum.value,
            options: [
              {key: 'unselected', value:''},
              {key: 'kieserver', value:'kieserver'},
            ],
            required: true,
            order: 1
          });
          break;
        case "CheckboxQuestion":
          question = new CheckboxQuestion({
            key: datum.name,
            label: datum.name,
            value: datum.value,
            required: true,
            order: 2
          });
          break;
        default:
          console.log("No question for data type");
          console.log(datum.type);
      }

      this.questions.push(question);
    });

    console.log(this.questions);

    return this.questions;
  }

}
