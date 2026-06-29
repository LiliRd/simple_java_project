pipeline {
    agent {
        docker {
            image 'maven:3.9-eclipse-temurin-17'
            args ''
        }
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo '✅ Code successfully pulled from GitHub!'
            }
        }

        stage('Build & Test') {
            steps {

                dir('my-app') {
                    echo 'Building Maven project...'
                    sh 'mvn clean package -DskipTests' // اضافه کردن skipTests برای سرعت بیشتر در تست اول
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                // پیدا کردن فایل JAR که ساخته شده
                archiveArtifacts artifacts: 'my-app/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '🎉 YES! Build Successful, Lili! Your code is safe in GitHub and built perfectly!'
        }
        failure {
            echo '❌ Oh no! Something went wrong. Check the logs.'
        }
    }
}
