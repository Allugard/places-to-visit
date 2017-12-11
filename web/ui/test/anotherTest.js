var assert = require('assert');

var calc = require('./calculator.js');

describe('Calculator Tests', function() {
	it('returns 2-2=0', function(done) {
    	assert.equal(calc.subtraction(2, 2), 0);
    	done();
    });


	it('returns 2+2=4', function(done) {
		assert.equal(calc.adding(2, 2), 4);
		done();
	});

	it('returns 2*2=4', function(done) {
		assert.equal(calc.multiplying(2, 2), 4);
		done();
	});

	it('returns 2/2=1', function(done) {
    	assert.equal(calc.division(2, 2), 1);
    	done();
    });
});