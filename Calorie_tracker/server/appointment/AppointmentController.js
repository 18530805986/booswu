var express = require("express");
var router = express.Router();
var bodyParser = require("body-parser");
var mongoose = require("mongoose");
var moment = require("moment");
var globalResponses = require("../config").globalResponses;
var collectionNames = require("../config").collectionNames;
var serverKey = require("../config").serverKey;
var ObjectID = require("mongodb").ObjectID;
var db = mongoose.connection;
var sendNotification = require("../notification/sendNotification");
router.use(
  bodyParser.urlencoded({
    extended: false
  })
);
router.use(bodyParser.json());

//-----------------------------------------------------------------------
//                            Imports
//-----------------------------------------------------------------------
//get user appointments

router.post("/getappointments", (web_req, web_res) => {
 
  if(web_req.body.user_id!=null){

    db.collection(collectionNames.appointments)
      .find({user_id:web_req.body.user_id})
      .sort({creation_date:-1})
      .toArray((err,res)=>{

        web_res.json({data:res,result:1})

      });
  }
  else{
    web_res.json(globalResponses.invalidParametersErrorResponse);

  }

});

router.post("/bookappointment",(web_req,web_res)=>{

  if(web_req.body.user_id!=null&&web_req.body.user_name!=null&&web_req.body.appointment_date&&web_req.body.doctor_id&&web_req.body.doctor_name){

    appointmentInstance={

      user_id:web_req.body.user_id,
      user_name:web_req.body.user_name,
      appointment_date: parseInt( web_req.body.appointment_date),
      doctor_id:web_req.body.doctor_id,
      doctor_name:web_req.body.doctor_name,
      appointment_date:parseInt(web_req.body.appointment_date),
      creation_date:new Date().getTime(),
      status:"confirmed"

    };
    db.collection(collectionNames.appointments)
      .insertOne(appointmentInstance,(err,res)=>{


        if(res!=null){

          web_res.json({message:"Appointment Created Successfully...",result:1,data:appointmentInstance});
        }
        else{
          web_res.json(globalResponses.standardErrorResponse);
        }

      });


  }
  else{
    web_res.json(globalResponses.invalidParametersErrorResponse);
  }


});


router.post("/cancelappointment",(web_req,web_res)=>{
  
    if(web_req.body.id!=null)
    {
 
      var myquery = { _id: ObjectID(web_req.body.id) };
      var newvalues = { $set: {status: "cancelled" } };
      db.collection(collectionNames.appointments)
      .updateOne(myquery, newvalues, function(err, res) {
        
        if(res!=null){

          web_res.json({message:"Successfully cancelled your appointment",result:1,status:"cancelled"})

        }
        else{

          web_res.json(globalResponses.standardErrorResponse);
        }


      });
  
    }
    else{
      web_res.json(globalResponses.invalidParametersErrorResponse);
    }
  
  
  });
  

module.exports = router;
