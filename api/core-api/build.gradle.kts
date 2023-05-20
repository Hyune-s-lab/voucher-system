plugins {

}

dependencies {
    val openapi3Version: String by project

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // openapi3
    implementation("org.springdoc:springdoc-openapi-ui:$openapi3Version")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:$openapi3Version")
    implementation("org.springdoc:springdoc-openapi-kotlin:$openapi3Version")
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}
