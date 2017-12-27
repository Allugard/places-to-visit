//
//  ViewController.swift
//  Places To Visit
//
//  Created by Nick Baidikoff on 11/15/17.
//  Copyright © 2017 Nick Baidikoff. All rights reserved.
//

import UIKit
import MaterialComponents

class LoginViewController: UIViewController {
    @IBOutlet weak var emailTextField: MDCTextField!
    @IBOutlet weak var passwordTextField: MDCTextField!
    
    @IBOutlet weak var signInButton: MDCRaisedButton!
    
    private lazy var emailController = MDCTextInputControllerLegacyDefault(textInput: self.emailTextField)
    private lazy var passwordController = MDCTextInputControllerLegacyDefault(textInput: self.passwordTextField)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .navigationBar
        self.view.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(dismissKeyboard)))
        
        self.configure(textField: self.emailTextField)
        self.configure(textField: self.passwordTextField)
        self.configureButton()
    }
    
    private func configure(textField: MDCTextField) {
        textField.textColor = .black
        textField.underline?.color = .black
    }
    
    private func configureButton() {
        self.signInButton.backgroundColor = .black
        self.signInButton.setTitleColor(.navigationBar, for: .normal)
    }
    
    @objc private func dismissKeyboard() {
        self.view.endEditing(true)
    }
    
    @IBAction func signIn(_ sender: Any) {
        let placesController = PlacesViewController()
        self.present(placesController, animated: true, completion: nil)
    }
}
