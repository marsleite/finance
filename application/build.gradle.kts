plugins {
    kotlin("jvm") version "1.8.22"
}

dependencies {
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("dev.miku:r2dbc-mysql:0.8.2.RELEASE")
    implementation("org.springframework:spring-core")
    testRuntimeOnly("com.h2database:h2")
    runtimeOnly("dev.miku:r2dbc-mysql")
    runtimeOnly("io.r2dbc:r2dbc-h2")
    runtimeOnly("mysql:mysql-connector-java:8.0.+")

    runtimeOnly("dev.miku:r2dbc-mysql:0.8.2.RELEASE")
    runtimeOnly("mysql:mysql-connector-java:8.0.33")

    testRuntimeOnly("com.h2database:h2")
    testRuntimeOnly("io.r2dbc:r2dbc-h2")

    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:2.2.5.RELEASE")
}