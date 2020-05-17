var express = require("express");
var router = express.Router();
var bodyParser = require("body-parser");
var mongoose = require("mongoose");
var globalResponses = require("../config").globalResponses;
var collectionNames = require("../config").collectionNames;
var defaultValues = require("../config").defaultValues;
var db = mongoose.connection;
var firebase = require("firebase");
var ObjectID = require("mongodb").ObjectID;
router.use(
  bodyParser.urlencoded({
    extended: false
  })
);
router.use(bodyParser.json());

//get one user details

router.post("/getuserdetails", (web_req, web_res) => {
  db.collection(collectionNames.users).findOne(
    { user_id: web_req.body.user_id },

    (err, res) => {
      if (err) web_res.json(globalResponses.standardErrorResponse);
      else web_res.json({ result: 1, data: res });
    }
  );
});



router.post("/getusers", (web_req, web_res) => {
  db.collection(collectionNames.users)
    .find()
    .sort({ creation_date: -1 })
    .toArray((err, res) => {
      if (err) web_res.json(globalResponses.standardErrorResponse);
      else web_res.json({ result: 1, data: res });
    });
});




//create user
//user_id
//user_name
//user_gcm_id
//user_email
//user_mobile
router.post("/createuser", (web_req, web_res) => {
  if (
    web_req.body.user_id != null &&
    web_req.body.user_name != null &&
    web_req.body.user_gcm_id != null &&
    web_req.body.user_email != null &&
    web_req.body.user_mobile != null &&
    web_req.body.user_photo_url != null
  ) {
    db.collection(collectionNames.users).findOne(
      { user_id: web_req.body.user_id },
      (err, exis_user) => {
        //user already exsists
        if (exis_user != null) {
          if (web_req.body.user_name !== "")
            exis_user.user_name = web_req.body.user_name;

          if (web_req.body.user_gcm_id !== "")
            exis_user.user_gcm_id = web_req.body.user_gcm_id;

          if (web_req.body.user_email !== "")
            exis_user.user_email = web_req.body.user_email;

          if (web_req.body.user_mobile !== "")
            exis_user.user_mobile = web_req.body.user_mobile;

          // if (web_req.body.user_photo_url !== "")
          //   exis_user.user_photo_url = web_req.body.user_photo_url;

          db.collection(collectionNames.users).updateOne(
            { user_id: exis_user.user_id },
            { $set: exis_user },
            (err, res) => {
              if (err) web_res.json(globalResponses.standardErrorResponse);
              else {
                userCreationSuccessResponse =
                  globalResponses.userCreationSuccessResponse;
                userCreationSuccessResponse["data"] = exis_user;
                web_res.json(userCreationSuccessResponse);
              }
            }
          );
        }
        //new user
        else {
          userInstance = {
            user_id: web_req.body.user_id,
            user_name: web_req.body.user_name,
            user_gcm_id: web_req.body.user_gcm_id,
            user_email: web_req.body.user_email,
            user_mobile: web_req.body.user_mobile,
            user_photo_url: web_req.body.user_photo_url,
            creation_date: new Date().getTime(),
            ink: defaultValues.newUserInk
          };
          db.collection(collectionNames.users).insertOne(
            userInstance,
            (err, res) => {
              if (err) web_res.json(globalResponses.standardErrorResponse);
              else {
                userCreationSuccessResponse =
                  globalResponses.userCreationSuccessResponse;
                userCreationSuccessResponse["data"] = userInstance;
                web_res.json(userCreationSuccessResponse);
              }
            }
          );
        }
      }
    );
  } else {
    web_res.json(globalResponses.invalidParametersErrorResponse);
  }
});

module.exports = router;
