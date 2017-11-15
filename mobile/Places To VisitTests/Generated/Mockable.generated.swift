// Generated using Sourcery 0.9.0 — https://github.com/krzysztofzablocki/Sourcery
// DO NOT EDIT

import XCTest
@testable import PlacesToVisit

class DataPresenterMock: DataPresenter {

    fileprivate var showData_data_String_called = false
    fileprivate var showData_data_String_then: ((String?) -> Void)?
    fileprivate var showData_data_String_data_received: String?

    override func showData(_ data: String) {
        self.showData_data_String_called = true
        self.showData_data_String_data_received = data
        self.showData_data_String_then?(data) 
    }

    fileprivate var showError_called = false
    fileprivate var showError_then: (() -> Void)?

    override func showError() {
        self.showError_called = true
        self.showError_then?() 
    }
}

class VerifyDataPresenterMock {
    private let object: DataPresenterMock

    init(object: DataPresenterMock) {
        self.object = object
    }

    class VerifyDataPresenterShowData_data_String {
        private let object: DataPresenterMock

        init(object: DataPresenterMock) {
            self.object = object
        }

        @discardableResult func data(_ value: String) -> VerifyDataPresenterShowData_data_String {
            XCTAssertEqual(self.object.showData_data_String_data_received, value, "expected data to be \(value)")
            return self
        }
    }

    @discardableResult func showData_data_String() -> VerifyDataPresenterShowData_data_String {
        XCTAssertTrue(self.object.showData_data_String_called, "expected showData to be called")
        return VerifyDataPresenterShowData_data_String(object: self.object)
    }

    func showError() {
        XCTAssertTrue(self.object.showError_called, "expected showError to be called")
    }
}

func verify(_ object: DataPresenterMock) -> VerifyDataPresenterMock {
    return VerifyDataPresenterMock(object: object)
}

class BehaviourDataPresenterMock {
    private let object: DataPresenterMock

    init(object: DataPresenterMock) {
        self.object = object
    }

    class BehaviourDataPresenterShowData_data_String {
        private let object: DataPresenterMock

        init(object: DataPresenterMock) {
            self.object = object
        }

        func then(_ completion: @escaping ((String?) -> Void)) {
            self.object.showData_data_String_then = completion
        }
    }

    func showData_data_String() -> BehaviourDataPresenterShowData_data_String {
        return BehaviourDataPresenterShowData_data_String(object: self.object)
    }

    class BehaviourDataPresenterShowError {
        private let object: DataPresenterMock

        init(object: DataPresenterMock) {
            self.object = object
        }

        func then(_ completion: @escaping (() -> Void)) {
            self.object.showError_then = completion
        }
    }

    func showError() -> BehaviourDataPresenterShowError {
        return BehaviourDataPresenterShowError(object: self.object)
    }
}

func when(_ object: DataPresenterMock) -> BehaviourDataPresenterMock {
    return BehaviourDataPresenterMock(object: object)
}

class InputDataProviderMock: InputDataProvider {

    fileprivate var fetch_called = false
    fileprivate var fetch_then: (() -> String?)?

    override func fetch() -> String? {
        self.fetch_called = true
        return self.fetch_then?() ?? super.fetch() 
    }
}

class VerifyInputDataProviderMock {
    private let object: InputDataProviderMock

    init(object: InputDataProviderMock) {
        self.object = object
    }

    func fetch() {
        XCTAssertTrue(self.object.fetch_called, "expected fetch to be called")
    }
}

func verify(_ object: InputDataProviderMock) -> VerifyInputDataProviderMock {
    return VerifyInputDataProviderMock(object: object)
}

class BehaviourInputDataProviderMock {
    private let object: InputDataProviderMock

    init(object: InputDataProviderMock) {
        self.object = object
    }

    class BehaviourInputDataProviderFetch {
        private let object: InputDataProviderMock

        init(object: InputDataProviderMock) {
            self.object = object
        }

        func then(_ completion: @escaping (() -> String?)) {
            self.object.fetch_then = completion
        }
    }

    func fetch() -> BehaviourInputDataProviderFetch {
        return BehaviourInputDataProviderFetch(object: self.object)
    }
}

func when(_ object: InputDataProviderMock) -> BehaviourInputDataProviderMock {
    return BehaviourInputDataProviderMock(object: object)
}

