import {TestBed} from '@angular/core/testing';
import {AppComponent} from './app.component';
import {InputsComponent} from "./inputs.component";
import {RedblackgreenDirective} from "./redblackgreen.directive";
import {RoundingPipe} from "./rounding.pipe";

describe('InputsComponent', () => {

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        AppComponent, InputsComponent, RedblackgreenDirective, RoundingPipe
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(InputsComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

});
