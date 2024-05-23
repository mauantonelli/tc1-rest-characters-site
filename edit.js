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

        const name = document.getElementById('name').value;
        const className = document.getElementById('class').value;
        const level = document.getElementById('level').value;
        const index = document.getElementById('index').value;

        const character = {
            name,
            className,
            level
        };

        if (index !== null) {
            updateCharacter(character, index);
        }

        window.location.href = 'lista.html';
    });

    function updateCharacter(character, index) {
        let characters = JSON.parse(localStorage.getItem('characters')) || [];
        characters[index] = character;
        localStorage.setItem('characters', JSON.stringify(characters));
    }
});