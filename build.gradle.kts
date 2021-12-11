
repositories {
    mavenCentral()
}

plugins {
    `java-library`
    idea
    jacoco
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

jacoco {
    toolVersion = "0.8.7"
}

allprojects {
    group = "com.kata.pocker"

    tasks.withType<Jar> {
        enabled = false
    }
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.12.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")
}

tasks {
    withType<Test>().all {
        testLogging {
            setExceptionFormat("full")
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
        useJUnitPlatform {
            includeEngines("junit-jupiter", "junit-vintage")
        }
        maxHeapSize = "1500m"
        jvmArgs("--enable-preview")
    }

    withType<JavaCompile> {
        options.compilerArgs.add("--enable-preview")
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(false)
    }
    dependsOn(tasks.test) // tests are required to run before generating the report
}



