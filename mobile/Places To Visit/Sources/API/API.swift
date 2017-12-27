//
//  API.swift
//  PlacesToVisit
//
//  Created by Nick Baidikoff on 12/27/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import Foundation
import Alamofire

class API {
    static let shared = API()
    private init() {}
    
    private let baseUrl = "http://localhost:9001"
    private var token = ""
    
    enum Method {
        case signIn, signUp, logout
        case places, addPlace, deletePlace, deletePlaces
        
        var route: String {
            switch self {
            case .signIn: return "/login"
            case .signUp: return "/sign-up"
            case .logout: return "/logout"
            case .places, .deletePlaces: return "/user/places"
            case .addPlace, .deletePlace: return "/user/place"
            }
        }
        
        var httpMethod: HTTPMethod {
            switch self {
            case .signIn, .logout: return .post
            case .signUp, .addPlace: return .put
            case .places: return .get
            case .deletePlace, .deletePlaces: return .delete
            }
        }
    }
    
    func update(token: String) {
        self.token = token
    }
    
    func perform(_ method: Method, parameters: Parameters? = nil, completion: (DefaultDataResponse) -> Void) {
        Alamofire.request(self.baseUrl + method.route, method: method.httpMethod, parameters: parameters, headers: [
            "Authorization": "Bearer\(self.token)"
        ]).response(completion)
    }
}
