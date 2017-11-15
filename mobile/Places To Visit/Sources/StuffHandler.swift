//
//  StuffHandler.swift
//  Places To Visit
//
//  Created by Nick Baidikoff on 11/15/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import Foundation

class StuffHandler {
    private let dataProvider: InputDataProvider
    private let dataPresenter: DataPresenter
    
    init(dataProvider: InputDataProvider = InputDataProvider(), dataPresenter: DataPresenter = DataPresenter()) {
        self.dataPresenter = dataPresenter
        self.dataProvider = dataProvider
    }
    
    func handle() {
        guard let data = dataProvider.fetch() else {
            self.dataPresenter.showError()
            return
        }
        
        self.dataPresenter.showData(data)
    }
}
