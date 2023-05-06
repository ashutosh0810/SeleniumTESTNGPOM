# Selenium TestNG Framework
This is a Selenium TestNG automation framework for testing AUT web applications. The framework uses Selenium WebDriver for browser automation, TestNG for test execution and management, and Extent Reports for reporting.

# Features
* Page Object Model (POM) design pattern for easy maintenance and readability
* Page Factory for initializing page objects
* Data-driven testing using TestNG DataProvider and external Excel file.
* Parallel test execution on different browsers
* Extent Reports for detailed test reports with error screenshots only for failed test cases.
* Configuration management using properties files
* Utility methods for common actions, such as waiting and page load functionalities.
* Logging using Log4j.

# Requirements
JDK 14 
Maven 3.6 or higher

# Directory Structure

<pre>
project/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   ├── pomPages/
│   │   │   └── utils/
│   │   └── resources/
│   │       ├── config.properties
│   │       ├── log4j2.xml
│   │       └── testData.xlsx
│   └── test/
│       ├── java/
│       │   └── lumaProject/
│       │       └── tests/
│       └── resources/
│
├── target/
├── testng.xml
├── pom.xml
└── README.md
</pre>

# Setup
**Clone the repository:  `This is not yet implemented`**
<pre>
git clone https://github.com/yourusername/selenium-testng-framework.git

</pre>
* git clone https://github.com/yourusername/selenium-testng-framework.git
* Import the project into your favorite IDE as a Maven project.
* Update the config.properties file with the desired test configurations (e.g., browser, URLs, etc.).
* Ensure that the TestData.xlsx file contains the required test data for login tests.



# Running Tests
**From IDE**
Open the testng.xml file in your IDE.
Right-click on the file and select "Run 'testng.xml'" (or similar option depending on the IDE).

**From Command Line**
Open a terminal/command prompt and navigate to the project directory.
Run the following command:
<pre>
mvn clean test
</pre>

#Test Reports
The test reports are generated using Extent Reports and can be found in the test-output/ExtentReports directory. Open the index.html file in a web browser to view the test results.