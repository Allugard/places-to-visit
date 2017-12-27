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
    private let camera = GMSCameraPosition.camera(withLatitude: 22.0, longitude: 22.0, zoom: 4.0)
    private lazy var map = GMSMapView.map(withFrame: CGRect.zero, camera: self.camera)
    private var markers = [GMSMarker]()
    
    private lazy var presenter = PlacesPresenter(view: self)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.configureMap()
        self.configureNavigationBar()
        
        self.presenter.getAllPlaces()
    }
    
    private func configureNavigationBar() {
        self.addChildViewController(self.navigationBar.headerViewController)
        self.navigationBar.headerViewController.headerView.backgroundColor = .navigationBar
        self.navigationBar.headerViewController.headerView.tintColor = .black
        self.navigationBar.addSubviewsToParent()
        
        self.title = Strings.PlacesView.Title
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(title: Strings.PlacesView.ClearButton, style: .plain, target: self, action: #selector(clear))
    }
    
    private func configureMap() {
        self.map.delegate = self
        self.view = self.map
    }
    
    @objc private func clear() {
        UIView.animate(withDuration: 0.5) {
            self.markers.forEach { $0.map = nil }
            self.markers = []
        }
    }
    
    private func createMarker(place: Place) -> GMSMarker {
        let marker = GMSMarker(position: CLLocationCoordinate2D(latitude: place.latitude, longitude: place.longitude))
        marker.appearAnimation = .pop
        marker.map = self.map
        return marker
    }
}

extension PlacesViewController: GMSMapViewDelegate {
    func mapView(_ mapView: GMSMapView, didTapAt coordinate: CLLocationCoordinate2D) {
        self.presenter.savePlace(latitude: coordinate.latitude, longitude: coordinate.longitude)
    }
}

extension PlacesViewController: PlacesView {
    func show(error: String) {
        self.showAlert(error: error)
    }
    
    func show(places: [Place]) {
        self.clear()
        self.markers = places.map(self.createMarker)
    }
    
    func add(place: Place) {
        self.markers.append(self.createMarker(place: place))
    }
}
