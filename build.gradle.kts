import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	id("org.springframework.boot") version "2.7.2" apply false
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}


allprojects {
	/**/
	group = "tw.elliot"
	version = "1.0"

	repositories {
		mavenCentral()
	}

}



subprojects {

	println("Enabling Java And Kotlin in project ${project.name}...")

	println("Enabling Spring Boot Dependency Management in project ${project.name}...")

	apply(plugin = "java")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "kotlin")

	java.sourceCompatibility = JavaVersion.VERSION_17

	dependencyManagement {
		imports {
			mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
			mavenBom("de.codecentric:spring-boot-admin-dependencies:2.7.3")
		}

		dependencies {
			dependency("com.google.guava:guava:31.1-jre")
		}
	}

	dependencies {
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}

	tasks.withType<KotlinCompile> {
		println("Configuring KotlinCompile  $name in project ${project.name}...")
		kotlinOptions {
			jvmTarget = "17"
		}
	}

	tasks.test {
		useJUnitPlatform()
	}

}

tasks.jar {
	enabled = false
}
