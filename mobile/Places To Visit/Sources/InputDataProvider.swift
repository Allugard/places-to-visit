//
//  InputDataProvider.swift
//  Places To Visit
//
//  Created by Nick Baidikoff on 11/15/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import Foundation

class InputDataProvider: Mockable {
    func fetch() -> String? {
        return Double(arc4random() / UINT32_MAX) > 0.5 ? "some data" : nil
    }
}

