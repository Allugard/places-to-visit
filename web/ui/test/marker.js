exports.createMarker = function(markerName, propertyName, propertyValue){
    markerName[propertyName] = propertyValue;
	return markerName;
};


