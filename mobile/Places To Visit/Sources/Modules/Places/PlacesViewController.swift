//
//  PlacesViewController.swift
//  PlacesToVisit
//
//  Created by Nick Baidikoff on 12/27/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import Foundation
import UIKit
import GoogleMaps
import MaterialComponents
import CoreLocation

class PlacesViewController: UIViewController {
    private let navigationBar = MDCAppBar()
    private let camera = GMSCameraPosition.camera(withLatitude: 22.0, longitude: 22.0, zoom: 6.0)
    private lazy var map = GMSMapView.map(withFrame: CGRect.zero, camera: self.camera)
    private var markers = [GMSMarker]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.configureMap()
        self.configureNavigationBar()
    }
    
    private func configureNavigationBar() {
        self.addChildViewController(self.navigationBar.headerViewController)
        self.navigationBar.headerViewController.headerView.backgroundColor = .navigationBar
        self.navigationBar.addSubviewsToParent()
        self.title = "My Places"
    }
    
    private func configureMap() {
        self.map.delegate = self
        self.view = self.map
    }
}

extension PlacesViewController: GMSMapViewDelegate {
    func mapView(_ mapView: GMSMapView, didTapAt coordinate: CLLocationCoordinate2D) {
        let marker = GMSMarker(position: coordinate)
        marker.appearAnimation = .pop
        marker.map = self.map
        
        let location = CLLocation(latitude: coordinate.latitude, longitude: coordinate.longitude)
        CLGeocoder().reverseGeocodeLocation(location) { (placemarks, error) in
            guard let placemark = placemarks?.first, let locality = placemark.locality, let country = placemark.country else {
                return
            }
            
            marker.title = placemark.thoroughfare
            marker.snippet = "\(locality), \(country)"
        }
        
        self.markers.append(marker)
    }
}
