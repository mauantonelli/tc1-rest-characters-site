document.addEventListener('DOMContentLoaded', function() {
    const characterList = document.getElementById('character-list');

    function deleteCharacter(index) {
        const confirmDelete = confirm('Você tem certeza que deseja excluir este personagem?');
        if (confirmDelete) {
            let characters = JSON.parse(localStorage.getItem('characters')) || [];
            characters.splice(index, 1);
            localStorage.setItem('characters', JSON.stringify(characters));
            renderCharacterList();
            alert('Personagem excluído com sucesso!');
        }
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

    // Make functions global
    window.editCharacter = editCharacter;
    window.deleteCharacter = deleteCharacter;

    renderCharacterList();
});
