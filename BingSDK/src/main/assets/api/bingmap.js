var infoboxArray = [];

function onScriptLoad(){
JsBingMapScriptLoadInterface.onScriptLoadSuccess();
}

var map;

function loadMapScenario(key) {
map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
  credentials: key
});

map.setOptions({
  showScalebar: false,
  showCopyright: false,
  enableSearchLogo: false,
  enableClickableLogo: false,
  showZoomButtons:false,
  showDashboard: false
});
Microsoft.Maps.Events.addHandler(map, 'click', function (args) {
   JsBingMapClickInterface.onMapClick(JSON.stringify(args.location));
});
JsBingMapReadyInterface.onMapReady();
}

function setLocation(latitude, longitude) {
map.setView({
  center: new Microsoft.Maps.Location(latitude, longitude),
  zoom: 15
});
}

function addPushpin(javaPushpin){
var pushpin = new Microsoft.Maps.Pushpin(javaPushpin.location, javaPushpin.pushpinoptions);
JsPushpinAddInterface.onPushpinAdded();
Microsoft.Maps.Events.addHandler(pushpin, 'click', function(args){
  JsPushpinClickInterface.onPushpinClick(args);
});

if(typeof javaPushpin.infobox !== 'undefined' ) {
  var infobox = new Microsoft.Maps.Infobox(pushpin.location, { visible: false });
  infoboxArray[infoboxArray.length] = infobox; // add to global
  infobox.id = javaPushpin.id; //infobox id may be equal to pushpin id


  pushpin.metadata = javaPushpin.infobox.infoboxoptions;
  Microsoft.Maps.Events.addHandler(pushpin, 'click', function (args) {
    // currently support onlu one Label - index 0 in array
    if(typeof args.target.metadata.actions!=='undefined'){
      args.target.metadata.actions[0].eventHandler = function() {
        JsInfoboxActionInterface.onLabelClick(javaPushpin.id)
      };
    }
    infobox.setOptions({
      location: args.target.getLocation(),
      visible: true
    });
    infobox.setOptions(args.target.metadata);
  });
  infobox.setMap(map);
}
map.entities.push(pushpin);
}

function addPushpinsArray(javaPushpinsArray){

for (var i = 0; i < javaPushpinsArray.length; i++) {
  var pushpin = new Microsoft.Maps.Pushpin(javaPushpinsArray[i].location, javaPushpinsArray[i].pushpinoptions);
  if(typeof avaPushpinsArray[i].infobox !== 'undefined' ) {
    var infobox = new Microsoft.Maps.Infobox(javaPushpinsArray[i].location, {visible: false});
    if(typeof avaPushpinsArray[i].actions[0] !== 'undefined' ) {
      (function() {
        try {
          var closurePushpin = pushpin;
          javaPushpinsArray[i].infobox.infoboxoptions.actions[0].eventHandler = function(args) {
            console.log(args);
            JsInfoboxActionInterface.onLabelClick(closurePushpin.id)
          };

        } catch (err) {
        }
      })();
    }
    infobox.setMap(map);


    pushpin.metadata = javaPushpinsArray[i].infobox.infoboxoptions;
    pushpin.infobox = infobox;

    pushpin.id = javaPushpinsArray[i].id;
    infobox.id = pushpin.id;
    infoboxArray[infoboxArray.length] = infobox;

    Microsoft.Maps.Events.addHandler(pushpin, 'click', function (args) {
      pushpin.infobox.setOptions({
        location: args.target.getLocation(),
        visible: true
      });

      pushpin.infobox.setOptions(args.target.metadata);
    });
  }
  map.entities.push(pushpin);
}
}

function showInfobox(javaInfobox){
javaInfobox.visible=true;
var infobox = new Microsoft.Maps.Infobox(map.getCenter(), javaInfobox.infoboxoptions);
infobox.id = -1;
infoboxArray[infoboxArray.length] = infobox;
infobox.setMap(map);
}

function clearPushpins(){
for (var i = map.entities.getLength() - 1; i >= 0; i--) {
  var pushpin = map.entities.get(i);
  map.entities.removeAt(i);
}

for (var i = infoboxArray.length - 1; i >= 0; i--) {
  infoboxArray[i].setMap(null);
}
JsPushpinRemoveInterface.onPushpinRemove();
}

function addPushpinClickListener(){
for (var i = map.entities.getLength() - 1; i >= 0; i--) {
  var pushpin = map.entities.get(i);
  Microsoft.Maps.Events.addHandler(pushpin, 'click', function (args) {
    JsPushpinClickInterface.onPushpinClick(args)
  });
}
}

function moveCamera(cameraUpdate){
if(cameraUpdate.location != "") {
  var bounds = Microsoft.Maps.LocationRect.fromLocations(cameraUpdate.location);
  cameraUpdate.viewoptions.bounds = bounds;
}
map.setView(cameraUpdate.viewoptions);
}

function getScreenLocation(javaLocation){
var location  = map.getCenter();
location.latitude = javaLocation.latitude;
location.longitude = javaLocation.longitude;
var point = map.tryLocationToPixel(location, Microsoft.Maps.PixelReference.control);

JsBingScreenLocationInterface.onPoint(point.x,point.y);
}

function updateViewOptions(viewOptions){
map.setView(viewOptions);
}

function updateMapOptions(mapOptions){
map.setOptions(mapOptions);
}