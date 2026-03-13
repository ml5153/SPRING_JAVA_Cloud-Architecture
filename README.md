# 팀원 소개 서비스 (Cloud Architecture 실습)

AWS 인프라와 Spring Boot를 활용한 팀원 정보 관리 및 이미지 업로드 서비스입니다.
CI/CD 파이프라인과 Docker 컨테이너 기술을 적용하여 배포 자동화를 구현했습니다.

## 🔗 프로젝트 요약
- **EC2 퍼블릭 IP:** `3.35.173.57`
- **Actuator Health:** [http://3.35.173.57:8080/actuator/health](http://3.35.173.57:8080/actuator/health)
- **Actuator Info:** [http://3.35.173.57:8080/actuator/info](http://3.35.173.57:8080/actuator/info)

---

## ✅ 과제 수행 결과 (LV 0 ~ LV 4)

### LV 0. 요금 폭탄 방지
- AWS Budgets 설정 완료 ($100 예산, 80% 도달 시 알림)
- [첨부: AWS Budgets 설정 캡처]

### LV 1 & 2. 인프라 및 DB 보안
- **VPC 설계:** Public/Private Subnet 분리 및 보안 그룹 설정
- **RDS 보안:** EC2의 보안 그룹 ID(sg-xxxx)만 허용하여 DB 보안 강화
- **Parameter Store:** DB 접속 정보 및 `team-name` 파라미터 관리
- [첨부: RDS 인바운드 규칙 보안 그룹 스크린샷]

### LV 3. S3 이미지 업로드 (Presigned URL)
- IAM Role을 EC2에 부여하여 Access Key 없이 S3 접근 권한 관리
- **이미지 URL 확인 (7일 유효):** [여기에 발급받은 Presigned URL 입력]
- **만료 시간:** [URL의 만료 날짜 및 시간 입력]
- [첨부: S3 이미지 접근 성공 스크린샷]

### LV 4. Docker & CI/CD 파이프라인
- **Docker:** Multi-platform 빌드(amd64/arm64) 지원 Dockerfile 작성
- **GitHub Actions:** CI(Build/Test) 및 CD(Docker Hub Push & EC2 Pull) 자동화 구축
- [첨부: GitHub Actions 성공 화면 캡처]
- [첨부: EC2 docker ps 실행 화면 캡처]