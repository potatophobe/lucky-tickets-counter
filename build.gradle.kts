plugins {
    kotlin("jvm") version "1.7.10"
}

group = "ru.potatophobe"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    systemProperties(System.getProperties().mapKeys { (k, _) -> k.toString() })
}
