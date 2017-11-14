//
//  StuffCalculatorTests.swift
//  Places To VisitTests
//
//  Created by Nick Baidikoff on 11/15/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import XCTest
@testable import Places_To_Visit

class StuffCalculatorTests: XCTestCase {
    private let instance = StuffCalculator()
    
    private let input1 = 1
    private let correctOutput1 = 2
    
    private let input2 = 3
    private let correctOutput2 = 6
    
    func testCalculatorPerformsCorrectCalculation1() {
        let output = self.instance.calculate(input: self.input1)
        XCTAssertEqual(output, self.correctOutput1, "expected output to be\(self.correctOutput1)")
    }
    
    func testCalculatorPerformsCorrectCalculation2() {
        let output = self.instance.calculate(input: self.input2)
        XCTAssertEqual(output, self.correctOutput2, "expected output to be\(self.correctOutput2)")
    }
}
