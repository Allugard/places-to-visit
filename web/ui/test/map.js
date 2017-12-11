var key = "";
var map = [];

exports.checkExistence = function(keyProperty){
    var status = false;
    for (var i = map.length - 1; i >= 0; i--) {
   	var marker = map[i];
        for(property in marker){
            if(marker[property] == key){
                status = true;
            }
        }
    };
	return status;
};

exports.addMarker = function(map, marker){
    map.push(marker);
    condition = true;
    return map;
};

exports.isUpdated = function(markersArray, prevLength){
    var updated = false;
    if(markersArray.length >= prevLength){
        prevLength = markersArray.length;
        updated = true;
    }
    return updated;
};


