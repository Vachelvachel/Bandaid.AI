function signUpFunction() {
    app.get('/', async (req, res) => {
        res.redirect('/input');
    })
}

function logInFunction() {
    app.get('/', async (req, res) => {
        res.redirect('/input');
    })
};

function uploadFiles() {
    const input = document.getElementById('fileInput');
    const files = input.files;

    if (!files.length) {
        displayMessage('No files selected.', 'red');
        return;
    }

    const formData = new FormData();
    for (let file of files) {
        formData.append('upload[]', file);
    }

    const myDB = client.db("AI_Entry");
    const myColl = myDB.collection("Images");

    fetch('upload.php', {
        method: 'POST',
        body: formData,
    })
        .then(res => res.json())
        .then(data => {
            if (data.status === 'success') {
                displayMessage('Upload successful!', 'green');
            } else {
                displayMessage(data.error || 'Upload failed.', 'red');
            }
        })
        .catch(() => displayMessage('Upload error occurred.', 'red'));
}

function displayMessage(msg, color) {
    const box = document.getElementById('message');
    box.innerText = msg;
    box.style.color = color;
}
