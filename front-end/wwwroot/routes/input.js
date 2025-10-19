import express from 'express';
import fs from 'fs';
var router = express.Router();

router.get('/', async (req, res) => {
    res.redirect('/site');
})

router.get('/site', async(req, res) =>{
    res.type('html');
    res.sendFile('index.html', {root: 'wwwroot'});
})

router.get('/site/*splat', async(req, res) =>{
    let fileType = req.url.substring(req.url.lastIndexOf("."));
    res.type(fileType);
    let fileURL = req.url.replace("/site", "");
    res.sendFile(fileURL, {root: 'wwwroot'});
})

router.get('/input', async(req, res) =>{
    res.type('html');
    res.sendFile('input.html', {root: 'wwwroot'});
});

router.get('/sign-in', async(req, res) =>{
    res.type('html');
    res.sendFile('sign-in.html', {root: 'wwwroot'});
});

router.get('/file_upload', async(req, res) =>{
    console.log(req.params);
    console.log(req.body)
    
    res.send("success")
});

router.get('/result', async(req, res) =>{
    res.type('html');
    res.sendFile('result.html', {root: 'wwwroot'});
});

export default router