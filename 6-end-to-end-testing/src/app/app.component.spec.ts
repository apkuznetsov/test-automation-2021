import {ComponentFixture, TestBed} from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import {AppComponent, CalcData} from './app.component';
import {InputsComponent} from "./inputs.component";
import {RedblackgreenDirective} from "./redblackgreen.directive";
import {RoundingPipe} from "./rounding.pipe";

describe('AppComponent', () => {

  let appFixt: ComponentFixture<AppComponent>;
  let appComp: AppComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        AppComponent, InputsComponent, RedblackgreenDirective, RoundingPipe
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    appFixt = TestBed.createComponent(AppComponent);
    appComp = appFixt.componentInstance;
    appFixt.detectChanges();
  });

  it('сложение 41 + 19 = 60', () => {
    const calcData: CalcData = {
      num1: 41,
      num2: 19,
      selectedValue: "Сложить"
    };

    appComp.calc(calcData)

    expect(appComp.num3).toBe(60);
  });

  it('вычитание -92 - 93 = 1', () => {
    const calcData: CalcData = {
      num1: -92,
      num2: 93,
      selectedValue: "Вычесть"
    };

    appComp.calc(calcData)

    expect(appComp.num3).toBe(-185);
  });

  it('умножение 52 * 54 = 2808', () => {
    const calcData: CalcData = {
      num1: 52,
      num2: 54,
      selectedValue: "Умножить"
    };

    appComp.calc(calcData)

    expect(appComp.num3).toBe(2808);
  });

  it('деление 74 / 2 = 37', () => {
    const calcData: CalcData = {
      num1: 74,
      num2: 2,
      selectedValue: "Поделить"
    };

    appComp.calc(calcData)

    expect(appComp.num3).toBe(37);
  });

  it('имеется вывод расчёта', () => {
    const input3 = appFixt.debugElement.query(By.css('#input3'));
    expect(input3).toBeTruthy();
  });
});
