plugins {
    kotlin("jvm") version "1.8.22"
}

dependencies {
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("dev.miku:r2dbc-mysql:0.8.2.RELEASE")
    testRuntimeOnly("com.h2database:h2")
    runtimeOnly("dev.miku:r2dbc-mysql")
    runtimeOnly("io.r2dbc:r2dbc-h2")
    runtimeOnly("mysql:mysql-connector-java:8.0.+")
}