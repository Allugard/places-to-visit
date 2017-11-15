var mockit = require('mockit');
var assert = require('assert');

var marker = mockit('./marker.js');
var map = mockit('./map.js');

var markerToCompare = new Object();

mapMarkers = [{location: 'Warsaw'},{location:'Riga'},{location: 'Vilnius'},{location: 'Tallinn'}];
prevLength = mapMarkers.length;

describe('Map Tests with mocks', function() {
	it('returns true if map is updated with new marker', function(done) {
        marker.createMarker( markerToCompare, 'location', 'Kiev');
        if(!(map.checkExistence(mapMarkers, markerToCompare.location))){
            map.addMarker(mapMarkers, markerToCompare);
        }
        assert.equal(map.isUpdated(mapMarkers, prevLength), true);
    	done();
    });
});