plugins {
    kotlin("jvm") version "1.8.22"
}

apply(plugin = "project-report")

tasks {
    jar { enabled = true}
    bootJar { enabled = false }
}