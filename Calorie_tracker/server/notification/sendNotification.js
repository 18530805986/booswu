var mongoose = require("mongoose");
var collectionNames = require("./../config").collectionNames;
var db = mongoose.connection;
var serverKey = require("../config").serverKey;
var FCM = require("fcm-push");
var fcm = new FCM(serverKey);

pushNotification = (story_id, story_name, content, sender_name, receiver_id, type) => {
  var message = {
    to: "",
    collapse_key: "your_collapse_key",
    data: {
      your_custom_data_key: "your_custom_data_value",
      id: story_id,
      type:type 
     },
    notification: {
      title: story_name,
      click_action:"OPEN_ACTIVITY_1",
      body: "Body of your push notification"
    }
  };
  message.notification.body = content;

  db.collection(collectionNames.users).findOne({
      user_id: receiver_id
    },
    (err, res) => {
      if (res != null) {
        message.to = res.user_gcm_id;
        fcm.send(message, function (err, response) {

        });
      }
    }
  );
};

sendNotification = (
  successCallback,
  failureCallback,
  sender_id,
  sender_name,
  receiver_id,
  story_id,
  story_name,
  type
) => {

  switch (type) {
    case 1:
      content = sender_name + " liked your extension";
      break;
    case 2:
      content = sender_name + " extended your story";
      break;
    case 99:
      content = sender_name;
      break;
  }
  notificationInstance = {
    sender_id: sender_id,
    sender_name: sender_name,
    receiver_id: receiver_id,
    content: content,
    story_id: story_id,
    type: type,
    creation_date: new Date().getTime()
  };
  db.collection(collectionNames.notifications).insertOne(
    notificationInstance,
    (err, res) => {
      if (err) failureCallback();
      else {
        successCallback();
        pushNotification(story_id, story_name,content, sender_name, receiver_id, type);
      }
    }
  );
};
module.exports = sendNotification