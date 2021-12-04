import {ComponentFixture, TestBed} from '@angular/core/testing';
import {AppComponent} from './app.component';
import {InputsComponent} from "./inputs.component";
import {RedblackgreenDirective} from "./redblackgreen.directive";
import {RoundingPipe} from "./rounding.pipe";
import {By} from "@angular/platform-browser";

describe('InputsComponent', () => {

  let component: InputsComponent;
  let fixture: ComponentFixture<InputsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        AppComponent, InputsComponent, RedblackgreenDirective, RoundingPipe
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InputsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('ввод букв в input 1', () => {
    const input1 = fixture.debugElement.query(By.css('#input1'));
    input1.nativeElement.value = 'qwerty';

    input1.nativeElement.dispatchEvent(new Event('input'));
    fixture.detectChanges();

    expect(input1.nativeElement.value).toBe('');
  });

  it('ввод числа 100 в input 1', () => {
    const input1 = fixture.debugElement.query(By.css('#input1'));
    input1.nativeElement.value = '100';

    input1.nativeElement.dispatchEvent(new Event('input'));
    fixture.detectChanges();

    expect(input1.nativeElement.value).toBe('100');
  });

  it('ввод букв в input 2', () => {
    const input2 = fixture.debugElement.query(By.css('#input2'));
    input2.nativeElement.value = 'qwerty';

    input2.nativeElement.dispatchEvent(new Event('input'));
    fixture.detectChanges();

    expect(input2.nativeElement.value).toBe('');
  });

  it('ввод числа 100 в input 2', () => {
    const input2 = fixture.debugElement.query(By.css('#input2'));
    input2.nativeElement.value = '100';

    input2.nativeElement.dispatchEvent(new Event('input'));
    fixture.detectChanges();

    expect(input2.nativeElement.value).toBe('100');
  });

  it('ввод нуля в input 2 при делении', () => {
    const input2 = fixture.debugElement.query(By.css('#input2'));
    input2.nativeElement.value = '0';
    const selector = fixture.debugElement.query(By.css('#operationSelector'));
    selector.nativeElement.value = "Поделить";

    input2.nativeElement.dispatchEvent(new Event('input'));
    fixture.detectChanges();

    expect(input2.nativeElement.value).toBe('');
  });

});
