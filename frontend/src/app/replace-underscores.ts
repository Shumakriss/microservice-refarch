import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'replaceUnderscores'})
export class ReplaceUnderscores implements PipeTransform {

  transform(value: string): string {
    let newValue = value.replace(/_/g, ' ');
    return `${newValue}`;
  }
  
}