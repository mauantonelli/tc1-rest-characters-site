document.addEventListener('DOMContentLoaded', function() {
    const characterList = document.getElementById('character-list');

    function deleteCharacter(index) {
        let characters = JSON.parse(localStorage.getItem('characters')) || [];
        characters.splice(index, 1);
        localStorage.setItem('characters', JSON.stringify(characters));
        renderCharacterList();
    }

    function editCharacter(index) {
        window.location.href = `edit.html?index=${index}`;
    }

    function renderCharacterList() {
        characterList.innerHTML = '';
        const characters = JSON.parse(localStorage.getItem('characters')) || [];
        characters.forEach((character, index) => {
            const li = document.createElement('li');
            li.innerHTML = `
                ${character.name} - ${character.className} - Nível ${character.level}
                <div>
                    <button class="edit" onclick="editCharacter(${index})">Editar</button>
                    <button class="delete" onclick="deleteCharacter(${index})">Excluir</button>
                </div>
            `;
            characterList.appendChild(li);
        });
    }

    window.editCharacter = editCharacter;
    window.deleteCharacter = deleteCharacter;

    renderCharacterList();
});