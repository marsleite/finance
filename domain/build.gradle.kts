plugins {
    kotlin("jvm") version "1.8.22"
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.assertj:assertj-core:3.23.1")
}

tasks {
    jar { enabled = true}
    bootJar { enabled = false }
}