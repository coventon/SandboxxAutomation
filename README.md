# SandboxxAutomationTest

# Selenium Mobile App Test Automation Framework

This is a README.md file for a Selenium-based mobile app test automation framework using Java, Java-Client 8.5, Appium 2.0, TestNG, Maven, UIAutomator, and XCUItest.

## Overview
This framework provides a foundation for automating mobile app testing using Selenium WebDriver, Java-Client, and Appium. It supports both Android and iOS platforms and utilizes UIAutomator for Android and XCUItest for iOS.

## Features
- Cross-platform support: The framework supports testing mobile applications on both Android and iOS platforms.
- UIAutomator for Android: It leverages UIAutomator, the native Android testing framework, to interact with and automate UI elements on Android devices.
- XCUItest for iOS: It uses XCUItest, the native testing framework for iOS, to interact with and automate UI elements on iOS devices.
- TestNG integration: The framework integrates TestNG, a popular testing framework for Java, to manage test cases and generate reports.
- Maven support: It is built using Maven, allowing easy dependency management and project configuration.
- Java-Client 8.5: The framework utilizes the Java-Client 8.5 library, which provides the necessary bindings to communicate with the Appium server.

## Prerequisites
Before running the tests using this framework, ensure you have the following components set up:

- Java Development Kit (JDK) 11
- Maven
- Appium Server 2.0 or later
- Android SDK for Android testing
- Xcode and XCUItest for iOS testing

## Setup Instructions
1. Clone the repository or download the framework files to your local machine.
2. Install the required dependencies mentioned in the `pom.xml` file using Maven.
3. Ensure the Appium server is up and running.
4. Connect your mobile devices (Android or iOS) to the testing machine or set up simulators/emulators.
5. Update the desired capabilities in the test configuration file (e.g., `testng.xml`) to match your testing environment.
6. Run the test suite using Maven or your preferred build tool:
   ```
   mvn clean test -DsuiteXmlFile=testng.xml
   ```

## Test Development
To create new test cases or modify existing ones, follow these guidelines:

1. Create a new Java class or modify existing ones under the test package.
2. Implement your test logic using Selenium WebDriver methods, along with the mobile-specific locators and actions.
3. Leverage the provided base test classes and utility functions for common actions like driver initialization and test setup/teardown.
4. Ensure proper assertion and verification mechanisms are in place to validate the expected behavior of the mobile app.

## Test Execution
To execute the test suite using the framework, follow these steps:

1. Ensure the Appium server is running and accessible.
2. Connect the mobile devices (Android or iOS) to the testing machine or set up simulators/emulators.
3. Update the desired capabilities in the test configuration file (`testng.xml`) according to your testing environment.
4. Run the test suite using Maven or your preferred build tool:
   ```
   mvn clean test -DsuiteXmlFile=testng.xml
   ```

## Reporting
The framework generates test reports using TestNG's default reporting mechanism. You can find the generated reports in the `target/surefire-reports` directory after test execution.

## Contributions
Contributions to enhance and expand the framework are welcome. If you find any issues or have suggestions for improvements, please submit an issue or pull request on the project's repository.

## License
This framework is released under the [Sandboxx.us](LICENSE).

## Contact
For any questions contact:
roman.nejouta@sandboxx.us, mike.gorelov@sandboxx.us