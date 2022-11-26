describe('Сквозное тестирование', () => {

    beforeEach(() => {
        cy.visit('/', {
            auth: {
                username: 'example@example.com',
                password: 'qwerty',
            },
        });
    });

    it('Добавление новой викторины', () => {
        cy.get('#my-title-tf').clear().type('Мой тематика');
        cy.get('#my-question-tf').clear().type('Мой вопрос');
        cy.get('#my-answer-tf').clear().type('Мой ответ');
        cy.get('#my-save-btn').click();
        cy.wait(1000);

        cy.get('.my-row').contains('Мой тематика').should('exist');
        cy.get('.my-row').contains('Мой вопрос').should('exist');
        cy.get('.my-row').contains('Мой ответ').should('exist');

        cy.get('.my-row').contains('Мой тематика').parent('.my-row')
            .find('.my-del-btn').click();
        cy.wait(1000)

        cy.get('.my-row').contains('Мой тематика').should('not.exist');
        cy.get('.my-row').contains('Мой вопрос').should('not.exist');
        cy.get('.my-row').contains('Мой ответ').should('not.exist');
    });

    it('Удаление викторины', () => {
        cy.get('#my-title-tf').type('Мой тематика');
        cy.get('#my-question-tf').type('Мой вопрос');
        cy.get('#my-answer-tf').type('Мой ответ');
        cy.get('#my-save-btn').click();
        cy.wait(1000);

        cy.get('.my-row').contains('Мой тематика').parent('.my-row')
            .find('.my-del-btn').click();
        cy.wait(1000)

        cy.get('.my-row').contains('Мой тематика').should('not.exist');
        cy.get('.my-row').contains('Мой вопрос').should('not.exist');
        cy.get('.my-row').contains('Мой ответ').should('not.exist');
    });

    it('Изменение викторины', () => {
        cy.get('#my-title-tf').type('Мой тематика');
        cy.get('#my-question-tf').type('Мой вопрос');
        cy.get('#my-answer-tf').type('Мой ответ');
        cy.get('#my-save-btn').click();
        cy.wait(1000);

        cy.get('.my-row').contains('Мой тематика').parent('.my-row')
            .find('.my-edit-btn').click();
        cy.wait(1000);

        cy.get('#my-title-tf').clear().type('Мой тематика 2');
        cy.get('#my-save-btn').click();
        cy.wait(1000);
        cy.reload();

        cy.get('.my-row').contains('Мой тематика 2').should('exist');

        cy.get('.my-row').contains('Мой тематика 2').parent('.my-row')
            .find('.my-del-btn').click();
    });
})