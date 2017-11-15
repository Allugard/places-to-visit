//
//  DataPresenter.swift
//  Places To Visit
//
//  Created by Nick Baidikoff on 11/15/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import Foundation

class DataPresenter: Mockable {
    func showData(_ data: String) {
        print(data)
    }
    
    func showError() {
        debugPrint("error")
    }
}
