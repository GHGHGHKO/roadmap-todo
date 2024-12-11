plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("com.google.cloud.tools.jib") version "3.4.4"
	id("org.springframework.boot") version "3.4.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.feelsgoodfrog"
version = "0.0.1-SNAPSHOT"

jib {
	from {
		image = "openjdk:21-jdk-slim"
	}
	to {
		image = "gudrb963/roadmap-todo"
		tags = setOf("latest")
	}
	container {
		jvmFlags = listOf("-XX:InitialRAMPercentage=75", "-XX:MaxRAMPercentage=85")
	}
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

extra["springCloudVersion"] = "2024.0.0-RC1"

val kotlinLoggingJvm = "7.0.0"
val jwt = "0.12.6"
val p6spy = "1.10.0"
val jsoup = "1.18.2"

dependencies {
	implementation("io.jsonwebtoken:jjwt-api:${jwt}")
	implementation("io.github.oshai:kotlin-logging-jvm:${kotlinLoggingJvm}")
	implementation("org.jsoup:jsoup:$jsoup")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:${p6spy}")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:${jwt}")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:${jwt}")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
