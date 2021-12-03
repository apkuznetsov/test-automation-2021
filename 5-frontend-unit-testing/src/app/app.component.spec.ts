import {TestBed} from '@angular/core/testing';
import {AppComponent, CalcData} from './app.component';
import {InputsComponent} from "./inputs.component";
import {RedblackgreenDirective} from "./redblackgreen.directive";
import {RoundingPipe} from "./rounding.pipe";

describe('AppComponent', () => {

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        AppComponent, InputsComponent, RedblackgreenDirective, RoundingPipe
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'Калькулятор'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('Калькулятор');
  });

  it(`should have as title 'Калькулятор'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('Калькулятор');
  });

  it('сложение 41 + 19 = 60', () => {
    const comp = new AppComponent();
    const calcData: CalcData = {
      num1: 41,
      num2: 19,
      selectedValue: "Сложить"
    };

    comp.calc(calcData)

    expect(comp.num3).toBe(60);
  });

  it('вычитание -92 - 93 = 1', () => {
    const comp = new AppComponent();
    const calcData: CalcData = {
      num1: -92,
      num2: 93,
      selectedValue: "Вычесть"
    };

    comp.calc(calcData)

    expect(comp.num3).toBe(-185);
  });

  it('умножение 52 * 54 = 2808', () => {
    const comp = new AppComponent();
    const calcData: CalcData = {
      num1: 52,
      num2: 54,
      selectedValue: "Умножить"
    };

    comp.calc(calcData)

    expect(comp.num3).toBe(2808);
  });

  it('деление 74 / 2 = 37', () => {
    const comp = new AppComponent();
    const calcData: CalcData = {
      num1: 74,
      num2: 2,
      selectedValue: "Поделить"
    };

    comp.calc(calcData)

    expect(comp.num3).toBe(37);
  });

});
