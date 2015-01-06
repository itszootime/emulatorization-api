# emulatorization-api

Java API for emulator design, training and validation. Can be used either as a library in an existing Java project, or as a web service (backend to the [emulatorization-web project](https://github.com/itszootime/emulatorization-web)).

## Requirements

- [MATLAB](mathworks.com/products/matlab/) and gpmlab-2.0 code.
- A running [matlab-connector](https://github.com/itszootime/matlab-connector) instance.
- A running [Rserve](http://rforge.net/Rserve/) instance.

## Usage as a libary

### Getting started

1. Add the dependency to your `pom.xml`:

    ```xml
    <dependencies>
      <!-- Other dependencies may be here too -->
      <dependency>
        <groupId>org.uncertweb</groupId>
        <artifactId>emulatorization-api</artifactId>
        <version>0.1-SNAPSHOT</version>
      </dependency>
    </dependencies>
    ```

    If you have troubles resolving the dependency, add the UncertWeb Maven repository to your list of repositories:

    ```xml
    <repositories>
      <!-- Other repositories may be here too -->
      <repository>
        <id>UncertWebMavenRepository</id>
        <name>UncertWeb Maven Repository</name>
        <url>http://giv-uw.uni-muenster.de/m2/repo</url>
      </repository>
    </repositories>
    ```

2. Create `et.yml` in your resource directory. The example below assumes you have the matlab-connector running locally on port 44444 and Rserve on port 55555:

    ```yaml
    matlab:
      host: localhost
      port: 44444
      # You can also substitute host, port for an HTTP proxy
      # proxy: http://somehost/someproxy
      gpml_path: /path/to/gpmlab-2.0

    rserve:
      host: localhost
      port: 55555

    webapp:
      url: http://localhost:8080/emulatorization-api
    ```

### Examples

#### Create a LHS design

```java
import org.uncertweb.et.design.LHSDesign;

public static void main(String[] args) {
  List<Input> inputs = new ArrayList<Input>();
  inputs.add(new VariableInput("A", 0.0, 1.0));

  Design design = LHSDesign.create(inputs, numSamples);

  for (Double point : design.getPoints("A")) {
    System.out.println(point);
  }
}
```

## Usage as a web service

### Build

1. Clone the repository.
2. Create `et.yml` in `src/main/resources`, as described above.
3. Run `mvn package` to create a deployable WAR file.

### Requests

Type | Purpose
GetProcessIdentifiers | Retrieve a list of supported process identifiers from a web service.
GetProcessDescription | Retrieve a description of a process on a web service.
Screening | Perform input screening on a web service process.
Design | Create a design for a set of inputs.
EvaluateProcess | Evaluate a web service process against a design.
TrainEmulator | Train an emulator using a design and process evaluation result.
EvaluateEmulator | Evaluate an emulator against a design.
Validation | Compare simulator and emulator evaluation results.

### Examples

## Further reading

Is available in my Ph.D. thesis, [Uncertainty Analysis in the Model Web (Jones, 2014)](https://research.aston.ac.uk/portal/en/theses/uncertainty-analysis-in-the-model-web(0f4f5b5d-aab9-4097-aff1-7efc31603613).html).
