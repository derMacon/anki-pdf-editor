## Steps for generating a new maven project
* Open Intellij -> new maven project (without using an archetype)
* In the GroupId put something like `com.<yourName>.<projName>`
* in the artifactId enter the project name
* manually ad an directory called META-INF in the `com.<yourName...` directory
* in this directory add a file called `MANIFEST.mf`
* enter the following lines: `Main-Class: <full.qualified.class.name>`
* append your top level `POM.xml` with the following lines and click clean -> package in the maven pproject view.

```
<build>
  <plugins>
    <plugin>
      <!-- Build an executable JAR -->
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <version>3.1.0</version>
      <configuration>
        <archive>
          <manifest>
            <addClasspath>true</addClasspath>
            <classpathPrefix>lib/</classpathPrefix>
            <mainClass>com.mypackage.MyClass</mainClass>
          </manifest>
        </archive>
      </configuration>
    </plugin>
  </plugins>
</build>
```

Further [information](https://stackoverflow.com/questions/9689793/cant-execute-jar-file-no-main-manifest-attribute)
