module.exports = {
  //database and app secrets
  secret: "supersecret",



  firebase_config : {
    apiKey: "AIzaSyCsJ4pUtvoH6W2suDHK-CCbNY2d3GiAr4M",
    authDomain: "baymaxhealthcare-abc.firebaseapp.com",
    databaseURL: "https://baymaxhealthcare-abc.firebaseio.com",
    projectId: "baymaxhealthcare-abc",
    storageBucket: "baymaxhealthcare-abc.appspot.com",
    messagingSenderId: "442965901947"
  },
  //dev
  // dbURI:
  //   "mongodb://admin:admin@cluster0-shard-00-00-a31cp.mongodb.net:27017,cluster0-shard-00-01-a31cp.mongodb.net:27017,cluster0-shard-00-02-a31cp.mongodb.net:27017/complit?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true",

  //prod
  //dbURI:"mongodb://admin:admin@cluster0-shard-00-00-a31cp.mongodb.net:27017,cluster0-shard-00-01-a31cp.mongodb.net:27017,cluster0-shard-00-02-a31cp.mongodb.net:27017/healthcare?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true",
  dbURI:"mongodb://Koushik_Katakam:Kou_4848@cluster0-qvm2n.mongodb.net/test?retryWrites=true&w=majority",
  serverKey: "AIzaSyDRCCr-L0X5YsHoYC3qpE1ZKQy5PCFDx7Y",
  //responses
  globalResponses: {
    standardErrorResponse: {
      message: "Something went wrong, please try again later",
      result: 0
    },

    invalidParametersErrorResponse: {
      message: "Invalid parameters",
      result: 0
    },

    userCreationSuccessResponse: {
      result: 1,
      message: "successfully created user"
      //   data: '' created user object
    },

    createSuccessResponse: {
      result: 1,
      message: "successfully created post"
      //   data: '' class object
    },
    successQueryResponse: {
      result: 1,
      message: "Operation executed successfully..."
    },

    insufficientInkResponse: {
      result: 1,
      message: "Insufficient Ink to create story"
    },
    approvalSuccessResponse: {
      result: 1,
      message: "Succesfully approved post"
    },
    rejectionSuccessResponse: {
      result: 1,
      message: "Successfully rejected post"
    }
  },

  //collection names
  collectionNames: {
    users: "users",
    stories: "stories",
    notifications: "notifications",
    appointments:"appointments"
  },


};
