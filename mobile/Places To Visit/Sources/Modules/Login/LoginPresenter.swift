//
//  LoginPresenter.swift
//  PlacesToVisit
//
//  Created by Nick Baidikoff on 12/27/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import Foundation

protocol LoginView: class {
    func show(error: String)
    func moveToPlaces()
}

class LoginPresenter {
    private weak var view: LoginView?
    
    init(view: LoginView) {
        self.view = view
    }
    
    func login(username: String, password: String) {
        API.shared.perform(.signIn, parameters: ["username": username, "password": password]) { (responce) in
            if let error = responce.error {
                self.view?.show(error: error.localizedDescription)
                return
            }

            if let data = responce.data {
                do {
                    let json = try JSONSerialization.jsonObject(with: data) as? [String: Any]
                    let token = (json?["token"] as? String) ?? ""
                    API.shared.update(token: token)
                    self.view?.moveToPlaces()
                } catch let error {
                    self.view?.show(error: error.localizedDescription)
                }
            }
        }
    }
}
