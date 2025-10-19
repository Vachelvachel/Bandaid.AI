import express from 'express';
import fs from 'fs';
import router from './wwwroot/routes/input.js';
import mongoose from 'mongoose';

var app = express();

app.use('/', router);

mongoose.connect('mongodb+srv://sophwa06_db_user:jY2QUhNLCVE8XrR3@dubhacks-test.wqvw1ns.mongodb.net/?retryWrites=true&w=majority&appName=DubHacks-Test');
const { Schema } = mongoose;

const userSchema = new Schema({
  userName: String, 
  password: String,
  phone: String,
  email: String,
  gender: Boolean,
  address: String,
  date: Date,
  emergencyContact: [{ contact: String, phoneNumber: String }],
  allergy: [String],
  vaccine: [String],
  medication: [String]
});

const userModel = mongoose.model('userModel', userSchema);

app.listen(3000, 'localhost', () =>{
    console.log('Express App: https://localhost:3000');
})
