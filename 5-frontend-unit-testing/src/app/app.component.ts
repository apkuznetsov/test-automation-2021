import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: any = 'Калькулятор';

  // @ts-ignore
  public num1: number;
  // @ts-ignore
  public num2: number;
  // @ts-ignore
  public num3: number;

  add() {
    this.num3 = this.num1 + this.num2;
  }

  sub() {
    this.num3 = this.num1 - this.num2;
  }

  mult() {
    this.num3 = this.num1 * this.num2;
  }

  div() {
    this.num3 = this.num1 / this.num2;
  }
}
