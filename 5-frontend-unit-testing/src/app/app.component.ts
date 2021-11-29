import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: any = 'Калькулятор';

  public num1: number = 1;
  public num2: number = 1;
  // @ts-ignore
  public num3: number;

  operations = [
    "Сложить",
    "Вычесть",
    "Поделить",
    "Умножить"
  ];
  selectedValue = (this.operations)[0];

  calc() {
    this.num2 = Number(this.num2);

    switch (this.selectedValue) {
      case (this.operations)[0]: {
        this.num3 = this.num1 + this.num2;
        break;
      }
      case (this.operations)[1]: {
        this.num3 = this.num1 - this.num2;
        break;
      }
      case (this.operations)[2]: {
        this.num3 = this.num1 / this.num2;
        break;
      }
      case (this.operations)[3]: {
        this.num3 = this.num1 * this.num2;
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
