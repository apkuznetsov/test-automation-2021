import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'rounding'})
export class RoundingPipe implements PipeTransform {
  transform(value: number, decPlacesNum = 1): number {
    return Number(value.toFixed(decPlacesNum));
  }
}
