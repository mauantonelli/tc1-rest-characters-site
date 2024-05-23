document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('character-form');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const name = document.getElementById('name').value;
        const className = document.getElementById('class').value;
        const level = document.getElementById('level').value;

        const character = {
            name,
            className,
            level
        };

        saveCharacter(character);
        form.reset();
    });

    function saveCharacter(character) {
        let characters = JSON.parse(localStorage.getItem('characters')) || [];
        characters.push(character);
        localStorage.setItem('characters', JSON.stringify(characters));
    }
});