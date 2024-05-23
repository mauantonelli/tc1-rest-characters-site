document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('character-form');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const name = document.getElementById('name').value.trim();
        const className = document.getElementById('class').value.trim();
        const level = document.getElementById('level').value.trim();

        if (name === '' || className === '' || level === '') {
            alert('Todos os campos são obrigatórios.');
            return;
        }

        const character = {
            name,
            className,
            level
        };

        saveCharacter(character);
        alert(`Personagem ${name} cadastrado com sucesso!`);
        form.reset();
    });

    function saveCharacter(character) {
        let characters = JSON.parse(localStorage.getItem('characters')) || [];
        characters.push(character);
        localStorage.setItem('characters', JSON.stringify(characters));
    }
});