import { TaskData } from './task-data';

export class Task {
  id: number;
  name: string;
  subject: string;
  description: string;
  status: string;
  priority: number;
  skipable: boolean;
  actualOwner: string;
  createdBy: string;
  processInstanceId: number;
  processId: string;
  containerId: string;
  parentId: number;

  inData: TaskData[];
  outData: TaskData[];

  inputs: object;
  outputs: object;
  inputTypes: object;
  outputTypes: object;
}