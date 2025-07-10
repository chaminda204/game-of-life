plugins {
    id("java")
    id("groovy")

}

group = "com.au.bgl.gameoflife"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}


dependencies {
    implementation("org.slf4j:slf4j-api:2.0.12")
    runtimeOnly("ch.qos.logback:logback-classic:1.4.14")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.apache.groovy:groovy-all:4.0.15")
    testImplementation("org.spockframework:spock-core:2.3-groovy-4.0")
}

tasks.test {
    useJUnitPlatform()
}