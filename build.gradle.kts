plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.11.0")
}

tasks.test {
    useTestNG()
}
