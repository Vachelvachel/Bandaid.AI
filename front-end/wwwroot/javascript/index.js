window.addEventListener("DOMContentLoaded", (event) => {
    const form = document.querySelector('form');
    const submitButton = document.getElementById('submit-input');
    const fileInput = document.getElementById('file-input');

    var data = new FormData(form)

    const getImageFile = (inputElement) => {
        return new Promise(resolve => {
            const reader = new FileReader()
            reader.onload = function () {
                resolve(reader.result)
            }
            reader.readAsDataURL(inputElement);
        })
    }

    form.addEventListener('submit', (event) => {
        event.preventDefault();

        fetch('/file_upload', {
            method: 'POST',
            body: data
        })
            .then(data => console.log('Upload successful:', data))
            .catch(error => console.error('Error uploading image:', error));
    });
    fileInput.addEventListener('change', async () => {
        const imageFile = await getImageFile(fileInput["files"][0])
        data.append("contents", imageFile);
    });
});



// mongoose.connect('mongodb+srv://sophwa06_db_user:jY2QUhNLCVE8XrR3@dubhacks-test.wqvw1ns.mongodb.net/?retryWrites=true&w=majority&appName=DubHacks-Test');
// const { Schema } = mongoose;

// async function uploadFiles() {
//     console.log('test')
//     const imgData = new FormData(fileInput)
//     const imgModel = mongoose.model('Image', { image: Mixed });
//     const image = new imgModel({ image: imgData });


//     try {
//         result = await image.save();
//         console.log(result);
//         mongoose.disconnect();
//     } catch (err) {
//         console.log(err)
//     }
// }