plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jogamp.org/deployment/maven")
    }
}

tasks.withType<JavaExec> {
    systemProperty("java.library.path", "library/processing/macos-aarch64")
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.processing/core
    implementation("org.processing:core:4.3.3")
    implementation(files("library/videoExport.jar"))
    implementation(files("library/gluegen-rt.jar"))
    implementation(files("library/jogl-all.jar"))
    // https://mvnrepository.com/artifact/org.reflections/reflections
    implementation("org.reflections:reflections:0.10.2")

    implementation(fileTree(mapOf("dir" to "library/processing", "include" to listOf("*.jar"))))


}

tasks.test {
    useJUnitPlatform()
}