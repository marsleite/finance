import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    id("org.jetbrains.kotlinx.kover") version "0.7.1"
}

group = "com.msl"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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

val excludeCoverage = listOf(
    "**/*\$logger\$*.class",
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
