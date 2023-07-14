import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    id("org.jetbrains.kotlinx.kover") version "0.7.1"
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "project-report")
    apply(plugin = "org.jetbrains.kotlinx.kover")
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("org.slf4j:slf4j-api:2.0.7")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
        testImplementation("io.mockk:mockk:1.13.3")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("PASSED", "SKIPPED", "FAILED")
        }
    }
}

dependencies{
    kover(project("domain"))
    kover(project("application"))
}

val excludeCoverage = listOf(
    "**/*\$logger\$*.class",
    "com.msl.finance.application.dto.*",
    "com.msl.finance.FinanceApplication.kt",
    "com.msl.finance.FinanceApplicationKt",
    "com.msl.finance.exception.*",
    "com.msl.finance.model.*",
    "com.msl.finance.application.beans.*",
    "com.msl.finance.resource.repository.entity.*"
)

koverReport {
    defaults{
        filters{
            excludes{
                classes(excludeCoverage)
            }
        }
        html{
            onCheck = true
            setReportDir(layout.buildDirectory.dir("reports/jacoco/test/html"))
        }
        xml{
            onCheck = true
            setReportFile(layout.buildDirectory.file("reports/jacoco/test/jacocoTestReport.xml"))
        }
    }
}

tasks.register("jacocoTestReport") {
    dependsOn("test", "koverHtmlReport", "koverXmlReport")
}

tasks {
    jar { enabled = false }
    bootJar { enabled = false }
}
