import {ComponentFixture, TestBed} from '@angular/core/testing';
import {RedblackgreenDirective} from "./redblackgreen.directive";
import {By} from "@angular/platform-browser";
import {Component} from "@angular/core";

@Component({
  template: `
    <input [input]="-1" [redblackgreen] id="input3" type="number"/>`
})
class DirectiveTestComponentRed {
}

describe('RedblackgreenDirective', () => {

  let fixt: ComponentFixture<DirectiveTestComponentRed>;

  beforeEach(() => {
    fixt = TestBed.configureTestingModule({
      declarations: [RedblackgreenDirective, DirectiveTestComponentRed]
    })
      .createComponent(DirectiveTestComponentRed);

    fixt.detectChanges();
  });

  it('цвет фона красный при вводе -1', () => {
    let des = fixt.debugElement.queryAll(By.directive(RedblackgreenDirective));
    const input = des[0].nativeElement as HTMLInputElement;
    expect(input.style.backgroundColor).toBe('red');
  });
});

@Component({
  template: `
    <input [input]="0" [redblackgreen] id="input3" type="number"/>`
})
class DirectiveTestComponentBlack {
}

describe('RedblackgreenDirective', () => {

  let fixt: ComponentFixture<DirectiveTestComponentBlack>;

  beforeEach(() => {
    fixt = TestBed.configureTestingModule({
      declarations: [RedblackgreenDirective, DirectiveTestComponentBlack]
    })
      .createComponent(DirectiveTestComponentBlack);

    fixt.detectChanges();
  });

  it('цвет фона чёрный при вводе 0', () => {
    let des = fixt.debugElement.queryAll(By.directive(RedblackgreenDirective));
    const input = des[0].nativeElement as HTMLInputElement;
    expect(input.style.backgroundColor).toBe('black');
  });
});

@Component({
  template: `
    <input [input]="1" [redblackgreen] id="input3" type="number"/>`
})
class DirectiveTestComponentGreen {
}

describe('RedblackgreenDirective', () => {

  let fixt: ComponentFixture<DirectiveTestComponentGreen>;

  beforeEach(() => {
    fixt = TestBed.configureTestingModule({
      declarations: [RedblackgreenDirective, DirectiveTestComponentGreen]
    })
      .createComponent(DirectiveTestComponentGreen);

    fixt.detectChanges();
  });

  it('цвет фона зелёный при вводе 1', () => {
    let des = fixt.debugElement.queryAll(By.directive(RedblackgreenDirective));
    const input = des[0].nativeElement as HTMLInputElement;
    expect(input.style.backgroundColor).toBe('green');
  });
});
