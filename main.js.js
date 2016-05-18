//my server code for bustime


var express  = require('express');
var Firebase = require('firebase');
var Geofire  = require('geofire');
var distance = require('google-distance');
distance.apiKey = 'AIzaSyB1CBXgrHbAEue6KeuR4UXe9RzsZDnkNAA';


//start my server as an express app
var app = express();

//making request and getting responses here
app.get('/',function(req,res) {
  //decalared my functions here
  serverInformation();
  getDriverLocation();
  getBusStopLocation();
  getDriverBusStopDistance();
  getDriversAverageDistance();


  res.send("Success: BusTime is connected! ");
});

//port for my app to listen from
app.listen(3000, function () {
  console.log('BusTime app listening on port 3000..');
});

  var onComplete = function(error) {
  if (error) {
    console.log('Synchronization failed');
  }
 else {
    console.log('Synchronization succeeded');
  }
};



//elaborate on my declared funcions here

//this funtion saves the current server's (about) informatoin in firebase
function serverInformation() {
var ref = new Firebase("https://bustimer.firebaseio.com/server");
ref.set({
  serverInformation: {
    language: "node.js",
    port: 3000
  }
});
}

//this function retrieves Driver locaiton from firebase
function getDriverLocation() {
  // Get a database reference to my location_of_interest
  var ref  = new Firebase("https://bustimer.firebaseio.com/location_of_interest/driver/l");


  // Attach an asynchronous callback to read the data at my location_of_interest
  ref.on("value", function(snapshot) {
    var driverLatitude= snapshot.exportVal();
    var currentDriverLatitude = driverLatitude[0];
    console.log("Driver's latitude is: " + currentDriverLatitude);
    var driverLongitude = snapshot.exportVal();
    var currentDriverLongitude = driverLatitude[1];
    console.log("Driver's longitude is: " + currentDriverLongitude);

  }, function (errorObject) {
    console.log("The read failed: " + errorObject.code);
  });

}

//this function  retrieves The Passenger's chosen bus stop locaiton from firebase
function getBusStopLocation() {
  // Get a database reference to my location_of_interest
  var ref  = new Firebase("https://bustimer.firebaseio.com/location_of_interest/bus_stop/l");


  // Attach an asynchronous callback to read the data at my location_of_interest
  ref.on("value", function(snapshot) {
    var busStopLatitude= snapshot.exportVal();
    currentBusStopLatitude = busStopLatitude[0];
    console.log("Bus Stop's latitude is: " + currentBusStopLatitude);
    var busStopLongitude = snapshot.exportVal();
        currentBusStopLongitude = busStopLongitude[1];
    console.log("Bus Stop's longitude is: " + currentBusStopLongitude);

  }, function (errorObject) {
    console.log("The read failed: " + errorObject.code);
  });



}

//this function calculates the distance from the driver to the passegner's chosesn location
function getDriverBusStopDistance(){
  //use the google-distance matrix API for nodejs
  distance.get(
  {
    origin: 'currentDriverLatitude, currentDriverLongitude',
    destination: 'currentBusStopLatitude, currentBusStopLongitude',
    mode: 'driving',
    units: 'metric',
    language: 'en',
  },
  function(err, data) {
    if (err) return console.log(err);
    console.log(data);
});

}

// //this function gets the average distancre of a driver
function getDriversAverageDistance(){
  for(var i=0; i<5; i++){
      //store five instances of a driver's change in location
  }

}
