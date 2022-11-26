import {RoundingPipe} from "./rounding.pipe";

describe('RoundingPipe', () => {

  const pipe = new RoundingPipe();

  it('transforms 1.23999999 to 1.24', () => {
    expect(pipe.transform(1.23999999, 2)).toBe(1.24);
  });

  it('transforms 373.532123 to 373.5', () => {
    expect(pipe.transform(373.532123, 1)).toBe(373.5);
  });

});
