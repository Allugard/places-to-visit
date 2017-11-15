//
//  StuffHandlerTests.swift
//  Places To VisitTests
//
//  Created by Nick Baidikoff on 11/15/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import XCTest
@testable import PlacesToVisit

class StuffHandlerTests: XCTestCase {
    private let dataProviderMock = InputDataProviderMock()
    private let dataPresenterMock = DataPresenterMock()
    
    private let someData = "someData"
    
    private lazy var instance = StuffHandler(dataProvider: self.dataProviderMock, dataPresenter: self.dataPresenterMock)
    
    func testHandlerShowsErrorOnNilData() {
        // GIVEN
        when(self.dataProviderMock).fetch().then { return nil }
        
        // WHEN
        self.instance.handle()
        
        // THEN
        verify(self.dataPresenterMock).showError()
    }
    
    func testHandlerShowsDataOnCorrectInput() {
        // GIVEN
        when(self.dataProviderMock).fetch().then { return self.someData }
        
        // WHEN
        self.instance.handle()
        
        // THEN
        verify(self.dataPresenterMock).showData_data_String().data(self.someData)
    }
}
