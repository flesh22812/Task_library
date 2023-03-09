const authorList = document.getElementById('author-list');
const authorForm = document.getElementById('author-form');
const saveAuthor = document.getElementById('save-author');
const updateAuthor = document.getElementById('update-author');
const cancelUpdate = document.getElementById('cancel-update');

// function to render authors to the UI
function renderAuthors(authors) {
    authorList.innerHTML = '';
    authors.forEach(function (author) {
        const tr = document.createElement('tr');
        tr.innerHTML = `
      <td>${author.id}</td>
      <td>${author.name}</td>
      <td>${author.surname}</td>
      <td>${author.patronymic}</td>
      <td>${author.dateOfBirth}</td>
    `;
        authorList.appendChild(tr);
    });
}

// function to fetch authors from the server
function getAuthors() {
    fetch('/authors')
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            renderAuthors(data);
        });
}

// function to add a new author
function addAuthor(author) {
    fetch('/authors', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(author)
    })
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            getAuthors();
            authorForm.reset();
        });
}

// function to update an existing author
function updateAuthorById(id, author) {
    fetch(`/authors/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(author)
    })
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            getAuthors();
            authorForm.reset();
            saveAuthor.style.display = 'inline';
            updateAuthor.style.display = 'none';
            cancelUpdate.style.display = 'none';
            authorForm.removeAttribute('data-id');
        });
}

// function to delete an author
function deleteAuthorById(id) {
    fetch(`/authors/${id}`, {
        method: 'DELETE'
    })
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {
            getAuthors();
        });
}

// function to handle form submission
function handleFormSubmit(e) {
    e.preventDefault();

    const author = {
        name: authorForm.name.value,
        surname: authorForm.surname.value,
        patronymic: authorForm.patronymic.value,
        dateOfBirth: authorForm.dateOfBirth.value
    };

    const id = authorForm.getAttribute('data-id');
    if (id) {
        updateAuthorById(id, author);
    } else {
        addAuthor(author);
    }
}

// function to handle edit button click
function handleEditClick(e) {
    const tr = e.target.closest('tr');
    const id = tr.querySelector('td').textContent;
    const name = tr.querySelector('td:nth-child(2)').textContent;
    const surname = tr.querySelector('td:nth-child(3)').textContent;
    const patronymic = tr.querySelector('td:nth-child(4)').textContent;
    const dateOfBirth = tr.querySelector('td:nth-child(5)').textContent;

    authorForm.name.value = name;
    authorForm.surname.value = surname;
    authorForm.patronymic.value = patronymic;
    authorForm.dateOfBirth.value = dateOfBirth;
    authorForm.setAttribute('data-id', id);

    saveAuthor.style.display = 'none';
    updateAuthor.style.display = 'inline';
    cancelUpdate.style.display = 'inline';
}

// function to handle cancel button click


function handleCancelUpdate() {
// clear form data
    clearFormData();
// hide cancel button
    document.getElementById("cancel-update").style.display = "none";
// hide update button
    document.getElementById("update-author").style.display = "none";
// show save button
    document.getElementById("save-author").style.display = "inline";
}

// function to fetch authors from API
function fetchAuthors() {
    fetch('http://localhost:8080/api/authors')
        .then(response => response.json())
        .then(authors => displayAuthors(authors));
}

// function to display authors in the table
function displayAuthors(authors) {
    const authorList = document.getElementById("author-list");
    authorList.innerHTML = "";
    for (let author of authors) {
        let tr = document.createElement("tr");
        tr.innerHTML = <td>${author.id}</td>
        <td>${author.name}</td>
        <td>${author.surname}</td>
        <td>${author.patronymic}</td>
        <td>${author.dateOfBirth}</td>
        <td>
            <button class="btn-edit" onClick="editAuthor(${author.id})">Edit</button>
            <button class="btn-delete" onClick="deleteAuthor(${author.id})">Delete</button>
        </td>;
        authorList.appendChild(tr);
    }
}

// function to add or update author
function addOrUpdateAuthor(author) {
    let url = "http://localhost:8080/api/authors";
    let method = "POST";
    if (author.id) {
        url += "/" + author.id;
        method = "PUT";
    }
    fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(author)
    })
        .then(response => response.json())
        .then(author => {
            clearFormData();
            fetchAuthors();
        });
}

// function to delete author
function deleteAuthor(id) {
    fetch('http://localhost:8080/api/authors/${id}', {
        method: "DELETE"
    })
        .then(response => {
            fetchAuthors();
        });
}

// function to edit author
function editAuthor(id) {
    fetch('http://localhost:8080/api/authors/${id}')
        .then(response => response.json())
        .then(author => {
            document.getElementById("id").value = author.id;
            document.getElementById("name").value = author.name;
            document.getElementById("surname").value = author.surname;
            document.getElementById("patronymic").value = author.patronymic;
            document.getElementById("dateOfBirth").value = author.dateOfBirth;
// show cancel button
            document.getElementById("cancel-update").style.display = "inline";
// show update button
            document.getElementById("update-author").style.display = "inline";
// hide save button
            document.getElementById("save-author").style.display = "none";
        });
}

// function to clear form data
function clearFormData() {
    document.getElementById("id").value = "";
    document.getElementById("name").value = "";
    document.getElementById("surname").value = "";
    document.getElementById("patronymic").value = "";
    document.getElementById("dateOfBirth").value = "";
}