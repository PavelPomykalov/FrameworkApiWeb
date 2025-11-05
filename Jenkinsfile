pipeline {
    agent any

    parameters {
        choice(name: 'TAG', choices: ['API', 'WEB', 'SMOKE'], description: 'Какой набор тестов запускать')
        choice(name: 'ENV', choices: ['dev', 'test'], description: 'На каком стенде запускать тесты')
    }

    environment {
        ALLURE_RESULTS = "build/allure-results"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
                sh "chmod +x gradlew"
            }
        }

        stage('Run tests') {
            steps {
                echo "Запуск тестов по тегу: ${params.TAG} на стенде: ${params.ENV}"

                script {
                    def taskName = ""
                    if (params.TAG == 'API') taskName = "testApi"
                    if (params.TAG == 'WEB') taskName = "testWeb"
                    if (params.TAG == 'SMOKE') taskName = "testSmoke"

                    sh "./gradlew clean ${taskName} -Dallure.results.directory=${ALLURE_RESULTS} -Denv=${params.ENV}"
                }
            }
        }

        stage('Allure Report') {
            steps {
                allure results: [[path: "${ALLURE_RESULTS}"]]
            }
        }
    }

    post {
        success {
            emailext (
                subject: "✅ Тесты успешно завершены (${params.TAG}, ${params.ENV})",
                body: """✔ Pipeline: ${env.JOB_NAME}
✔ Build: #${env.BUILD_NUMBER}
✔ Стенд: ${params.ENV}
✔ Тег: ${params.TAG}""",
                to: "pavel.pomikalov@yandex.ru"
            )
        }

        failure {
            emailext (
                subject: "❌ Тесты упали (${params.TAG}, ${params.ENV})",
                body: """❌ Pipeline: ${env.JOB_NAME}
❌ Build: #${env.BUILD_NUMBER}
❌ смотри логи""",
                to: "pavel.pomikalov@yandex.ru"
            )
        }
    }
}
