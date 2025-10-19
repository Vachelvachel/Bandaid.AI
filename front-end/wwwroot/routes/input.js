import express from 'express';
import fs from 'fs';
import mongoose from 'mongoose';
import multer from 'multer';

var router = express.Router();
let upload = multer({
     limits: { fieldSize: 25 * 1024 * 1024 }
});

const { Schema } = mongoose;

const imageSchema = new Schema({
    image: Buffer
})
const Image = mongoose.model('Image', imageSchema)

router.get('/', async (req, res) => {
    res.redirect('/site');
})

router.get('/site', async (req, res) => {
    res.type('html');
    res.sendFile('index.html', { root: 'front-end/wwwroot' });
})

router.get('/site/*splat', async (req, res) => {
    let fileType = req.url.substring(req.url.lastIndexOf("."));
    res.type(fileType);
    let fileURL = req.url.replace("/site", "");
    res.sendFile(fileURL, { root: 'front-end/wwwroot' });
})

router.get('/input', async (req, res) => {
    res.type('html');
    res.sendFile('input.html', { root: 'front-end/wwwroot' });
});

router.get('/sign-in', async (req, res) => {
    res.type('html');
    res.sendFile('sign-in.html', { root: 'front-end/wwwroot' });
});

router.post('/file_upload', upload.single('uploaded_file'), async (req, res) => {
    mongoose.connect('mongodb+srv://sophwa06_db_user:jY2QUhNLCVE8XrR3@dubhacks-test.wqvw1ns.mongodb.net/?retryWrites=true&w=majority&appName=DubHacks-Test');
    console.log("trying")
    console.log(mongoose.connection.readyState);
    console.log(req.file);
    const imageUpload = new Image({ image: req.body.contents })

    try {
        await imageUpload.save()
        mongoose.disconnect
        res.redirect('/result')
    } catch (error) {
        console.log(error)
    }
});

router.get('/result', async (req, res) => {
    res.type('html');
    res.sendFile('result.html', { root: 'front-end/wwwroot' });
});

export default router