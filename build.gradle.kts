plugins {
    id("io.micronaut.application") version "4.6.1"
    id("com.gradleup.shadow") version "8.3.9"
    id("io.micronaut.aot") version "4.6.1"
    id("io.micronaut.docker") version "4.6.1"

}

version = "0.1"
group = "com.programacion.distribuida"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("ch.qos.logback:logback-classic")


    annotationProcessor("io.micronaut.data:micronaut-data-processor")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    runtimeOnly("org.postgresql:postgresql")


    annotationProcessor("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")

    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("jakarta.validation:jakarta.validation-api")

    implementation("io.micronaut.kubernetes:micronaut-kubernetes-client")
    implementation("io.micronaut.kubernetes:micronaut-kubernetes-discovery-client")

//    // KUBERNETES (Dependencias de Runtime)
//    implementation("io.micronaut.kubernetes:micronaut-kubernetes-client")
//    implementation("io.micronaut.kubernetes:micronaut-kubernetes-discovery-client")

    implementation("io.micronaut.discovery:micronaut-discovery-client")

    runtimeOnly("org.yaml:snakeyaml")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


application {
    mainClass = "com.programacion.distribuida.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}


graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("junit5")

    processing {
        incremental(true)
        annotations("com.programacion.distribuida.*")
    }

    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }

}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}
tasks.named<com.bmuschko.gradle.docker.tasks.image.DockerBuildImage>("dockerBuild") {
    images.set(listOf("emil2245/${project.name}:${project.version}", "emil2245/${project.name}:latest"))
}
tasks.named<com.bmuschko.gradle.docker.tasks.image.DockerBuildImage>("dockerBuildNative") {
    images.set(listOf("emil2245/${project.name}:${project.version}", "emil2245/${project.name}:latest"))
}

