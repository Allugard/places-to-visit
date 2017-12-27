//
//  PlacesPresenter.swift
//  PlacesToVisit
//
//  Created by Nick Baidikoff on 12/27/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import Foundation
import UIKit
import CoreLocation

protocol PlacesView: class {
    func show(error: String)
    func show(places: [Place])
    func add(place: Place)
}

class PlacesPresenter {
    private weak var view: PlacesView?
    
    init(view: PlacesView) {
        self.view = view
    }
    
    func getAllPlaces() {
        API.shared.perform(.places) { (responce) in
            if let error = responce.error {
                self.view?.show(error: error.localizedDescription)
                return
            }
            
            if let data = responce.data {
                do {
                    let json = try JSONSerialization.jsonObject(with: data) as? [[String: Any]]
                    let places = json?.flatMap { dict -> Place? in
                        guard let id = dict["id"] as? Int, let latitude = dict["lattitude"] as? Double, let longitude = dict["longitude"] as? Double else {
                            return nil
                        }
                        
                        let place = Place(id: id, latitude: latitude, longitude: longitude)
                        self.fetchPlaceInfo(place: place)
                        return place
                    } ?? []
                    
                    self.view?.show(places: places)
                } catch let error {
                    self.view?.show(error: error.localizedDescription)
                }
            }
        }
    }
    
    func savePlace(latitude: Double, longitude: Double) {
        API.shared.perform(.addPlace,
                           parameters: ["latitude": latitude, "longitude": longitude],
                           completion: { (responce) in
            if let error = responce.error {
                self.view?.show(error: error.localizedDescription)
                return
            }
                            
            if let data = responce.data {
                do {
                    let json = try JSONSerialization.jsonObject(with: data) as? [String: Any]
                    guard let id = json?["id"] as? Int, let latitude = json?["lattitude"] as? Double, let longitude = json?["longitude"] as? Double else {
                            return
                    }
                        
                    let place = Place(id: id, latitude: latitude, longitude: longitude)
                    self.fetchPlaceInfo(place: place)
                    self.view?.add(place: place)
                } catch let error {
                    self.view?.show(error: error.localizedDescription)
                }
            }
        })
    }
    
    private func fetchPlaceInfo(place: Place) {
        let location = CLLocation(latitude: place.latitude, longitude: place.longitude)
        CLGeocoder().reverseGeocodeLocation(location) { (placemarks, error) in
            guard let placemark = placemarks?.first, let locality = placemark.locality, let country = placemark.country else {
                return
            }
            
            place.updateAddress(short: "\(locality), \(country)", detailed: placemark.thoroughfare ?? "")
        }
    }
}
