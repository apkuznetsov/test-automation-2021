import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string = 'Калькулятор';

  num3: number = 2;
  operations = [
    "Сложить",
    "Вычесть",
    "Поделить",
    "Умножить"
  ];

  calc(calcData: CalcData) {
    switch (calcData.selectedValue) {
      case (this.operations)[0]: {
        this.num3 = calcData.num1 + calcData.num2;
        break;
      }
      case (this.operations)[1]: {
        this.num3 = calcData.num1 - calcData.num2;
        break;
      }
      case (this.operations)[2]: {
        this.num3 = calcData.num1 / calcData.num2;
        break;
      }
      case (this.operations)[3]: {
        this.num3 = calcData.num1 * calcData.num2;
        break;
      }
    }
  }
}

export interface CalcData {
  num1: number;
  num2: number;
  selectedValue: string;
}
