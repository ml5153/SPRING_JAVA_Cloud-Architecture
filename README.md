# 🚀 AWS Cloud & Spring Boot 통합 배포 프로젝트

본 프로젝트는 로컬 개발 환경을 넘어 **AWS 클라우드 인프라**를 직접 설계하고, **Docker**와 **GitHub Actions**를 활용한 현대적인 **CI/CD 파이프라인**을 구축하여 실제 서비스 운영 환경을 구현한 프로젝트입니다.

---

## 👨‍🏫 프로젝트 소개
단순한 코드 작성을 넘어, 클라우드 네이티브 환경에서 발생할 수 있는 **아키텍처 설계, 보안 설정, 비용 관리, 배포 자동화** 문제를 해결하는 데 중점을 두었습니다.

* **진행 범위:** LV.0 ~ LV.4
* **핵심 목표:**
1. 관리형 서비스(RDS, S3)를 활용한 인프라 분리
2. IAM Role 기반의 안전한 권한 관리
3. 아키텍처 호환성을 고려한 Docker 배포 자동화

---

## 📚 기술 스택
| 구분 | 기술 |
| :--- | :--- |
| **Language / Framework** | Java 17, Spring Boot 3.2.4 |
| **Database** | MySQL (RDS), H2 (Local) |
| **Cloud / Infra** | AWS (EC2, VPC, RDS, S3, SSM) |
| **DevOps** | Docker, GitHub Actions, Docker Hub |

---

## 🏗 서비스 아키텍처 및 구현 기능

### 1. 비용 및 네트워크 보안 (LV.0 ~ LV.2)
* **AWS Budget:** 월 예산 $100 설정 및 80% 도달 시 이메일 알림 구성.
* **Network:** VPC 내 Public/Private Subnet 분리 및 EC2-RDS 간 보안 그룹 체이닝(Security Group Chaining) 적용.
* **Config:** AWS Parameter Store를 연동하여 DB 접속 정보 및 운영 환경 변수를 안전하게 관리.

### 2. 스토리지 및 권한 관리 (LV.3)
* **S3 Integration:** 서버 디스크가 아닌 S3 버킷에 프로필 이미지 업로드 구현.
* **Security:** Access Key 노출 방지를 위해 EC2에 IAM Role을 직접 부여.
* **Presigned URL:** 보안을 위해 7일 유효기간이 설정된 Presigned URL 발급 기능 구현.

### 3. Docker & CI/CD (LV.4)
* **Multi-platform Build:** Mac(ARM)과 EC2(AMD) 간의 호환성을 위해 `linux/amd64` 기반 빌드 자동화.
* **Automation:** GitHub push 발생 시 Build -> Image Push -> EC2 Deployment까지 이어지는 무중단 흐름 구축.

---
## 🔗 프로젝트 요약
- **EC2 퍼블릭 IP:** `3.35.173.57`
- **Actuator Health:** [http://3.35.173.57:8080/actuator/health](http://3.35.173.57:8080/actuator/health)
- **Actuator Info:** [http://3.35.173.57:8080/actuator/info](http://3.35.173.57:8080/actuator/info)

---

## 🛠 트러블 슈팅 (Troubleshooting)

### 🚨 Issue 1: 로컬-서버 간 CPU 아키텍처 불일치 (`exec format error`)
- **현상:** 로컬(M4 Mac)에서 빌드한 Docker 이미지가 EC2에서 실행되지 않고 즉시 종료됨.
- **원인:** 로컬과 서버의 CPU 아키텍처가 달라 발생한 호환성 문제.
- **해결:** GitHub Actions 워크플로우에 `docker/setup-qemu-action`과 `docker/setup-buildx-action`을 추가하여 **Multi-platform 빌드** 환경 구축.

### 🚨 Issue 2: AWS 서비스 연동 시 권한 거부 (500 Error)
- **현상:** Actuator `/info` 엔드포인트 접속 시 Parameter Store 접근 권한 문제로 500 에러 발생.
- **해결:** EC2 인스턴스에 `AmazonSSMReadOnlyAccess` 정책이 포함된 **IAM Role**을 연결하고, 컨테이너 환경 변수에 `SPRING_PROFILES_ACTIVE=prod`를 명시하여 해결.

### 🚨 Issue 3: Actuator 엔드포인트 노출 보안 설정
- **현상:** 배포 후 `/actuator/health`는 정상이나 `/actuator/info`는 접속 불가.
- **해결:** `application-prod.yml`의 `exposure.include` 설정에 `info`를 명시적으로 추가하여 엔드포인트 활성화.

---


## ✅ 수행 결과 

### LV 0. 요금 폭탄 방지
- AWS Budgets 설정 완료 ($100 예산, 80% 도달 시 알림)

- ![aws_budget.png](image/aws_budget.png)

### LV 1 & 2. 인프라 및 DB 보안
- **VPC 설계:** Public/Private Subnet 분리 및 보안 그룹 설정
- **RDS 보안:** EC2의 특정 보안 그룹 ID만 허용하여 DB 보안 강화
- **Parameter Store:** DB 접속 정보 및 `team-name` 파라미터 관리
- ![vpc.png](image/vpc.png)
- ![ec2_1.png](image/ec2_1.png)
- ![ec2_2.png](image/ec2_2.png)
- ![actuator:health.png](image/actuator_health.png)
- ![actuator:info.png](image/actuator_info.png)

### LV 3. S3 이미지 업로드 (Presigned URL)
- IAM Role을 EC2에 부여하여 Access Key 없이 S3 접근 권한 관리
- **만료 시간:** [만료 시간: 2026-03-20 11:11:35 (KST)]
- **이미지 URL 확인 (7일 유효):**

https://amzn-s3-yspark-bucket.s3.ap-northeast-2.amazonaws.com/ffc4092b-feec-4ad8-a851-108bca4bd829_pikachu.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20260313T021626Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604800&X-Amz-Credential=AKIAVLCVNDSL7HS5GRW2%2F20260313%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Signature=d5f59454f85eb65ab132bc9f69f59f26eeb48b02d0fd4dd08bcccbae58832753


- ![get_profile-image.png](image/get_profile-image.png)
- ![post_profile-image.png](image/post_profile-image.png)


### LV 4. Docker & CI/CD 파이프라인
- **GitHub Actions:** CI(Build/Test) 및 CD(Docker Hub Push & EC2 Pull) 자동화 구축
- **Docker:** Multi-platform 빌드(amd64/arm64) 지원 Dockerfile 작성
- ![git_actions.png](image/git_actions.png)
- ![sudo_docker_ps.png](image/sudo_docker_ps.png)