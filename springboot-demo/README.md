# springboot-demo

A minimal Spring Boot app for testing Jenkins CI/CD → EC2 deployment.

---

## Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/hello` | Smoke test – returns `{"message":"Hello from Spring Boot!","status":"UP"}` |
| GET | `/api/info` | App info – version, env, timestamp |
| GET | `/actuator/health` | Spring Boot health check |

---

## Run locally

```bash
# Build
mvn clean package

# Run
java -jar target/springboot-demo.jar

# Test
curl http://localhost:8080/api/hello
curl http://localhost:8080/api/info
curl http://localhost:8080/actuator/health
```

---

## Jenkins setup

### 1. Prerequisites on Jenkins server
- JDK 17 configured in **Manage Jenkins → Global Tool Configuration** as `JDK-17`
- Maven 3.9 configured as `Maven-3.9`
- **SSH Agent** plugin installed

### 2. Add EC2 SSH key credential
1. **Manage Jenkins → Credentials → System → Global → Add Credential**
2. Kind: **SSH Username with private key**
3. ID: `EC2_SSH_KEY`
4. Paste your EC2 `.pem` private key

### 3. Edit Jenkinsfile
Open `Jenkinsfile` and update:
```groovy
EC2_USER = 'ec2-user'          // or 'ubuntu' for Ubuntu AMIs
EC2_HOST = '< YOUR_EC2_IP >'   // your EC2 public IP or DNS
```

### 4. Create pipeline job
1. New Item → Pipeline
2. Pipeline → Definition: **Pipeline script from SCM**
3. SCM: Git → add your repo URL
4. Script Path: `Jenkinsfile`
5. Save → **Build Now**

---

## EC2 prerequisites

Run these once on your EC2 instance:

```bash
# Amazon Linux 2023 / RHEL
sudo yum install -y java-17-amazon-corretto

# Ubuntu
sudo apt-get install -y openjdk-17-jre-headless

# Create deploy directory
sudo mkdir -p /opt/springboot-demo
sudo chown ec2-user:ec2-user /opt/springboot-demo   # adjust user if needed

# Open port 8080 in EC2 Security Group (AWS Console → Security Groups → Inbound Rules)
```

---

## Run with Docker (optional)

```bash
docker build -t springboot-demo .
docker run -d -p 8080:8080 --name demo springboot-demo
curl http://localhost:8080/api/hello
```
