import { QuestionBase } from './question-base';

export class RadioButtonQuestion extends QuestionBase<string> {
  controlType = 'radio';
  type: boolean;

  constructor(options: {} = {}) {
    super(options);
    this.type = options['type'] || '';
  }
}
