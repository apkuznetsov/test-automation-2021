import {Directive, ElementRef, Input, OnChanges, SimpleChanges} from '@angular/core';

@Directive({
  selector: '[redblackgreen]'
})
export class RedblackgreenDirective implements OnChanges {
  // @ts-ignore
  @Input() public redblackgreen: any;
  @Input() public input: any;

  constructor(private el: ElementRef) {
    this.highlight('red');
  }

  ngOnChanges(changes: SimpleChanges) {
    if (this.input < 0) {
      this.highlight('red');
    } else if (this.input == 0) {
      this.highlight('black');
    } else if (this.input > 0) {
      this.highlight('green');
    }

    this.el.nativeElement.value = this.input;
  }

  private highlight(color: string) {
    this.el.nativeElement.style.backgroundColor = color;
  }
}
