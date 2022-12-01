# Omama app - test automation


## Description
Omama app - test automation project based on [Gurkensalat framework](https://innersource.accenture.com/projects/ROBO/repos/gurkensalat) (part of ROBO Automation Ecosystem)
[Gurkensalat v0.99 documentation](https://ts.accenture.com/:b:/r/sites/DACTQANewjoinerstrainings/Shared%20Documents/General/Gurkensalat%20Framework/Gurkensalat%20ver.%200.99/Gurkensalat_ver0.99_Documentation.pdf?csf=1&web=1&e=q2dk55)
## Installation

**Prerequisites: You have to install before execution:**

- JDK11 [e.g. OpenJDK](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot)
- [Maven](https://maven.apache.org/download.cgi)

##Test execution

Execute commands below in terminal from Project root directory (location of [pom.xml](pom.xml))

**Executing all tests cases on your local machine**

    mvn clean test

**Executing Smoke tests cases on your local machine**

    mvn clean test  -Dcucumber.options="--tags @Smoke"

**Executing tests cases on your local machine examples**

    mvn clean test -Dcucumber.options="--tags @Smoke"
    mvn clean test -Dcucumber.options="--tags '@Smoke and @Login'"
    mvn clean test -Dcucumber.options="--tags '@Positive or @Negative'"
    mvn clean test -Dcucumber.options="--tags @Positive"
    mvn clean test -Dcucumber.options="src/test/java/features/CestaVon_LoginAction.feature"


**Reporting**

Reports are stored in [reports](reports) folder, each run will generate folder __timestamp_Report_tag__ using [ExtentReport](https://www.extentreports.com/) library w/ screenshots
## Authors and acknowledgment
marek.soltes@accenture.com

martin.skrlik@accenture.com