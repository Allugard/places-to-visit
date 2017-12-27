//
//  Place.swift
//  PlacesToVisit
//
//  Created by Nick Baidikoff on 12/27/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import Foundation
import UIKit

class Place {
    let id: Int
    let latitude: Double
    let longitude: Double
    
    private(set) var detailedAddress: String
    private(set) var shortAddress: String
    
    init(id: Int, latitude: Double, longitude: Double) {
        self.id = id
        self.latitude = latitude
        self.longitude = longitude
        self.detailedAddress = ""
        self.shortAddress = ""
    }
    
    func updateAddress(short: String, detailed: String) {
        self.shortAddress = short
        self.detailedAddress = detailed
    }
}
