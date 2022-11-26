describe('Сквозное тестирование', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  it('Существует первое окно ввода', () => {
    cy.get('#input1').should('be.visible');
  });

  it('Существует второе окно ввода', () => {
    cy.get('#input2').should('be.visible');
  });

  it('Существует выпадающий список операций', () => {
    cy.get('#operationSelector').should('be.visible');
  });

  it('Существует кнопка расчёта', () => {
    cy.get('#calcButton').should('be.visible');
  });

  it('Корректный ответ сложения 41 + 19 = 60', () => {
    cy.get('#input1').clear().type('41');
    cy.get('#operationSelector').select('Сложить');
    cy.get('#input2').clear().type('19');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '60');
  });

  it('Корректный ответ вычитания -92 - 93 = -185', () => {
    cy.get('#input1').clear().type('-92');
    cy.get('#operationSelector').select('Вычесть');
    cy.get('#input2').clear().type('93');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '-185');
  });

  it('Корректный ответ вычитания умножения 52 * 54 = 2808', () => {
    cy.get('#input1').clear().type('52');
    cy.get('#operationSelector').select('Умножить');
    cy.get('#input2').clear().type('54');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '2808');
  });

  it('Корректный ответ деления 74 : 2 = 37', () => {
    cy.get('#input1').clear().type('74');
    cy.get('#operationSelector').select('Поделить');
    cy.get('#input2').clear().type('2');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.value', '37');
  });

  it('Пустая строка при вводе букв в ввод 1', () => {
    cy.get('#input1').clear().type('йцукен');

    cy.get('#input1').should('have.value', '');
  });

  it('Пустая строка при вводе букв в ввод 2', () => {
    cy.get('#input2').clear().type('йцукен');

    cy.get('#input2').should('have.value', '');
  });

  it('Возможно ввести 0 во второй ввод при операции сложения', () => {
    cy.get('#operationSelector').select('Сложить');
    cy.get('#input2').clear().type('0');

    cy.get('#input2').should('have.value', '0');
  });

  it('Невозможно ввести 0 во второй ввод при операции деления', () => {
    cy.get('#operationSelector').select('Поделить');
    cy.get('#input2').clear().type('0');

    cy.get('#input2').should('have.value', '');
  });

  it('Зелёный фон при ответе положительном числе (1 + 1 = 1)', () => {
    cy.get('#input1').clear().type('1');
    cy.get('#operationSelector').select('Сложить');
    cy.get('#input2').clear().type('1');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.css', 'background-color', 'rgb(0, 128, 0)');
  });

  it('Чёрный фон при ответе нуле (0 + 0 = 0)', () => {
    cy.get('#input1').clear().type('0');
    cy.get('#operationSelector').select('Сложить');
    cy.get('#input2').clear().type('0');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.css', 'background-color', 'rgb(0, 0, 0)');
  });

  it('Красный фон при отрицательном ответе (1 - 5 = -4)', () => {
    cy.get('#input1').clear().type('1');
    cy.get('#operationSelector').select('Вычесть');
    cy.get('#input2').clear().type('5');
    cy.get('#calcButton').click();

    cy.get('#input3').should('have.css', 'background-color', 'rgb(255, 0, 0)');
  });
})
