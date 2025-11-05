pipeline {
    agent any
    options {
        timestamps()
    }
    parameters {
        booleanParam(name: 'API', defaultValue: false, description: 'Запуск тестов с тегом API')
        booleanParam(name: 'SMOKE', defaultValue: false, description: 'Запуск тестов с тегом SMOKE')
        booleanParam(name: 'WEB', defaultValue: false, description: 'Запуск тестов с тегом WEB')
        booleanParam(name: 'UI', defaultValue: false, description: 'Запуск тестов с тегом UI')
    }
    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout') {
            steps {
                git url: 'https://github.com/PavelPomykalov/FrameworkApiWeb', branch: 'main'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def tasksToRun = []
                    if (params.API) tasksToRun << 'testApi'
                    if (params.SMOKE) tasksToRun << 'testSmoke'
                    if (params.WEB) tasksToRun << 'testWeb'
                    if (params.UI) tasksToRun << 'testUi' // если есть такая задача в Gradle

                    if (tasksToRun.isEmpty()) {
                        echo "Ни один тест не выбран. Пропускаем запуск."
                    } else {
                        echo "Запуск Gradle задач: ${tasksToRun.join(' ')}"
                        // очищаем результаты allure перед запуском
                        sh 'rm -rf build/allure-results'

                        // запуск выбранных задач Gradle
                        def result = sh(
                            script: "./gradlew ${tasksToRun.join(' ')} -Dallure.results.directory=build/allure-results || true",
                            returnStatus: true
                        )

                        // собираем Allure отчёт в любом случае
                        sh './gradlew allureReport'

                        if (result != 0) {
                            currentBuild.result = 'FAILURE'
                            echo "Некоторые тесты завершились с ошибками"
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            echo "Сборка завершена. Результаты Allure доступны в build/allure-report"
        }
    }
}
