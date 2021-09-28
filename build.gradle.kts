
repositories {
    jcenter()
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


