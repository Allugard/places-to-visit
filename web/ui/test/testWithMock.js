var mockito = require('jsmockito');
var assert = require('assert');
var map = require('./map');

var markerToCompare = new Object();

describe('Map Tests with mocks', function() {
	it('returns true if map is updated with new marker', function(done) {
	    var mockedMap = mockito.JsMockito.mock(map);
        mockito.JsMockito.when(mockedMap).checkExistence(markerToCompare.location).thenReturn(true);

        var result = mockedMap.checkExistence(markerToCompare.location);

        assert.equal(result, true);
    	done();
    });
});