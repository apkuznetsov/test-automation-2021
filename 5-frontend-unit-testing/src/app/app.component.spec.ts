import {TestBed} from '@angular/core/testing';
import {AppComponent} from './app.component';
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

});
