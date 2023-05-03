## Features of the project
### Reusable utilities and wrapper methods

![image](/readme/reusable.png)

### Page object model design pattern
![image](/readme/pom.png)

### Execution support
* Can execute each class/method separately 
*   Check [Execution recording](https://drive.google.com/drive/folders/1QUk_hOlrNDY2XrJTMumO8ZfpPdf7ZOnk?usp=sharingl)
* Or find the test suite XML file in the testng.xml* file and then click "Run As" > "TestNG Suite"
* Or you can use this command in the directory of the project ```mvn clean test```
  ![Alt Text](/readme/record.gif)
* Or can execute using GitHub actions workflows
* ![image](/readme/githubactionswfs.png)




### Logging support
* Logging of the steps in the generated HTML report
  ![image](/readme/logging-report.png)

* Logging of the steps in the console
  ![image](/readme/console-logging.png)

### Auto screenshot capturing on failures
![image](/readme/fail-screenshot.png)

### Auto generated readable HTML report
![image](/readme/report.png)

### CI/CD support
* GitHub Actions 
  * Check [Test execution workflow](.github/workflows/TestExecutionWF.yml)
    ![image](/readme/githubActions.png)

* Jenkins
  * The project use maven as a build tool and supports integration with Jenkins
    ![image](/readme/jenkines.png)

### Performance checks
* Auto capturing all performance metrics using selenium and Chrome dev tools and adding it the auto generated HTML report
    ![image](/readme/metrics.png)

* Performance check using selenium and page insights website,A screenshots is automatically taken and added to the auto generated report
  ![image](/readme/pagespeed.png)
* Performance check using automated workflow 
  *   Check [page speed workflow](.github/workflows/PerformanceScoreCheck.yml)
  *   ![image](/readme/pagespeedwf.png)





