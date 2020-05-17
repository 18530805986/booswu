var express = require("express");
var app = express();
var db = require("./db");
var mongoose = require("mongoose");
var globalResponses = require("./config").globalResponses;
var collectionNames = require("./config").collectionNames;
var db_instance = mongoose.connection;
global.__root = __dirname + "/";
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header(
    "Access-Control-Allow-Headers",
    "Origin, X-Requested-With, Content-Type, Accept"
  );
  next();
});

process.on("uncaughtException", function(error) {
  console.log("uncaughtException " + error);
});




app.get("/api", function(req, res) {
  res.status(200).send("API works.");
});

var UserController = require(__root + "user/UserController");
app.use("/api/user", UserController);

var StoriesController = require(__root + "appointment/AppointmentController");
app.use("/api/appointment", StoriesController);

var NotificationController = require(__root +"notification/NotificationController");
app.use("/api/notification", NotificationController);

// expressLogging = require('express-logging'),
// logger = require('logops');
// app.use(expressLogging(logger));


module.exports = app;
