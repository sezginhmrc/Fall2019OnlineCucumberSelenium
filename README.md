To run tests over the jenkins, specify following top-level maven target as a build part:

clean test -Dcucumber.options="--tags @driver"

you can specify any tags that are available in your project




To run smoke test use:

clean test -P Smoke

To start regression execute:

clean test -P Regression

To run feature files in parallel without limiting number of threads:

<parallel>methods</parallel>
<useUnlimitedThreads>true</useUnlimitedThreads>
<includes>
  <include>**/RegressionRunner.java</include>
</includes>


to specify browser use parameter -Dbrowser=browserType:
clean test -Dbrowser=firefox 

to specify environment, use parameter -Den=enType 
mvn test -Denv=qa2

to run regression on qa1 and chrome browser;

clean test -Dbrowser=chrome -Denv=qa1 -P Regression

Note ;add mvn before every commn if you are not running it on jenkins

mvn test -Dbrowser=firefox -Denv=qa2 -P Regression