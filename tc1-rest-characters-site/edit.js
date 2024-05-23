document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('character-form');
    const urlParams = new URLSearchParams(window.location.search);
    const index = urlParams.get('index');

    if (index !== null) {
        const characters = JSON.parse(localStorage.getItem('characters')) || [];
        const character = characters[index];
        document.getElementById('name').value = character.name;
        document.getElementById('class').value = character.className;
        document.getElementById('level').value = character.level;
        document.getElementById('index').value = index;
    }

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const name = document.getElementById('name').value.trim();
        const className = document.getElementById('class').value.trim();
        const level = document.getElementById('level').value.trim();
        const index = document.getElementById('index').value;

        if (name === '' || className === '' || level === '') {
            alert('Todos os campos são obrigatórios.');
            return;
        }

        const character = {
            name,
            className,
            level
        };

        updateCharacter(character, index);
        alert(`Personagem ${name} atualizado com sucesso!`);
        window.location.href = 'lista.html';
    });

    function updateCharacter(character, index) {
        let characters = JSON.parse(localStorage.getItem('characters')) || [];
        characters[index] = character;
        localStorage.setItem('characters', JSON.stringify(characters));
    }
});
