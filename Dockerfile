# 어떤 환경에서 시작할지 (Java 17 환경 선택)
#FROM amazoncorretto:17-al2023-headless
FROM eclipse-temurin:17-jdk
WORKDIR /app

# 그 jar 파일을 박스(이미지) 안으로 'app.jar'라는 이름으로 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 박스가 실행될 때 자바를 실행시키는 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]