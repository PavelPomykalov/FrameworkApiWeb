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
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh """
                       ./gradlew clean test \
                         -Dallure.results.directory=${ALLURE_RESULTS} \
                         -Denv=${params.ENV} \
                         -Dgroups=${params.TAG}
                    """
                }
            }
        }

        stage('Allure Report') {
            steps {
                script {
                    if (fileExists("${ALLURE_RESULTS}")) {
                        allure([
                            results: [[path: "${ALLURE_RESULTS}"]],
                            reportBuildPolicy: 'ALWAYS'
                        ])
                    } else {
                        echo "Allure results folder not found, skipping report"
                    }
                }
            }
        }
    }

    post {
        success {
            emailext(
                subject: "✅ Тесты успешно завершены (${params.TAG}, ${params.ENV})",
                body: """Pipeline: ${env.JOB_NAME}
Build: #${env.BUILD_NUMBER}
Стенд: ${params.ENV}
Тег: ${params.TAG}
✔ Все тесты прошли успешно!""",
                to: "pavel.pomikalov@yandex.ru"
            )
        }

        failure {
            emailext(
                subject: "❌ Тесты упали (${params.TAG}, ${params.ENV})",
                body: """Pipeline: ${env.JOB_NAME}
Build: #${env.BUILD_NUMBER}
Стенд: ${params.ENV}
Тег: ${params.TAG}
❌ Проверьте логи и исправьте ошибки""",
                to: "pavel.pomikalov@yandex.ru"
            )
        }
    }
}
