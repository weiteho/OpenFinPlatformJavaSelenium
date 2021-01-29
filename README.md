# OpenFin Platform Application with Selenium in Java


### How to build

1. install Maven
2. clone this repository 
3. run command: mvn clean install
4. the whole demo app will be zipped in ./target directory

### How to run

unzip generated zip file then...

#### Start chromedriver
1. open cmd window and cd to current directory
2. chromedriver_2_28.exe --verbose

#### Start http server at port 8080

1. open cmd window and cd to current directory
2. http-server -p 8080

#### Run demo app

1. open cmd window and cd to current directory
2. java -DRemoteDriverURL=http://localhost:9515 -DExecPath=RunOpenFin.bat -DExecArgs="--config=http://localhost:8080/platform/app.json" -jar OpenFinPlatformJavaSelenium-1.0-SNAPSHOT.jar

## License
MIT

The code in this repository is covered by the included license.

However, if you run this code, it may call on the OpenFin RVM or OpenFin Runtime, which are covered by OpenFin’s Developer, Community, and Enterprise licenses. You can learn more about OpenFin licensing at the links listed below or just email us at support@openfin.co with questions.

https://openfin.co/developer-agreement/ <br/>
https://openfin.co/licensing/

## Support
Please enter an issue in the repo for any questions or problems. Alternatively, please contact us at support@openfin.co 