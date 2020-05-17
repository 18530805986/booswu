var express = require("express");
var router = express.Router();
var bodyParser = require("body-parser");
var mongoose = require("mongoose");
var globalResponses = require("./../config").globalResponses;
var collectionNames = require("./../config").collectionNames;
var sendNotification = require("../notification/sendNotification");

var db = mongoose.connection;

router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());

//-----------------------------------------------------------------------
//                            Imports
//-----------------------------------------------------------------------

//get notifications given user_id
router.post("/getnotification", (web_req, web_res) => {
  if (web_req.body.user_id != null) {
    db.collection(collectionNames.notifications)
      .find({ receiver_id: web_req.body.user_id })
      .sort({ creation_date: -1 })
      .toArray((err, res) => {
        if (err) web_res.json(globalResponses.standardErrorResponse);
        else web_res.json({ result: 1, data: res });
      });
  } else {
    web_res.json(globalResponses.invalidParametersErrorResponse);
  }
});

//sendnotification given user_id
//content

//send admin notifications
router.post("/sendadminnotification", (web_req, web_res) => {
  if (web_req.body.content != null) {
    db.collection(collectionNames.users)
      .find()
      .toArray((err, res) => {
        if (err) web_res.json(globalResponses.standardErrorResponse);
        else {
          res.forEach(user => {
            sendNotification(
              () => {},
              () => {},
              "admin",
              web_req.body.content,
              user.user_id,
              web_req.body.story_id, //story_id
              web_req.body.title,
              99
            );
          });
          web_res.json(globalResponses.successQueryResponse);
        }
      });
  } else {
    web_res.json(globalResponses.invalidParametersErrorResponse);
  }
});

module.exports = router;
