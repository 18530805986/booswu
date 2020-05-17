var mongoose = require("mongoose");
console.log("db.js initiated....");
var config = require("./config"); // get our config file

const options = {
  useNewUrlParser: true,

  reconnectTries: Number.MAX_VALUE, // Never stop trying to reconnect
  reconnectInterval: 500, // Reconnect every 500ms

  poolSize: 10, // Maintain up to 10 socket connections
  // If not connected, return errors immediately rather than waiting for reconnect
  bufferMaxEntries: 0,
  connectTimeoutMS: 10000, // Give up initial connection after 10 seconds
  socketTimeoutMS: 45000, // Close sockets after 45 seconds of inactivity
  family: 4, // Use IPv4, skip trying IPv6
  useMongoClient: true,
  server: { auto_reconnect: true }
};

dbURI = config.dbURI;

var db = mongoose.connection;

db.on("connecting", function() {
  console.log("connecting to MongoDB...");
});

db.on("error", function(error) {
  console.error("Error in MongoDb connection: " + error);
  mongoose.disconnect();
});
db.on("connected", function() {
  console.log("MongoDB connected!");
});
db.once("open", function() {
  console.log("MongoDB connection opened!");
});
db.on("reconnected", function() {
  console.log("MongoDB reconnected!");
});
db.on("disconnected", function() {
  console.log("MongoDB disconnected!");
  mongoose.connect(
    dbURI,
    { server: { auto_reconnect: true } }
  );
});

mongoose.connect(
  dbURI,
  options,
  function(error) {
    // Check error in initial connection. There is no 2nd param to the callback.
    console.log("Database connection initiated...");
    if (error == null) console.log("Connection Successful...");
    else console.log("Error in connecting to database... " + error);
  }
);
