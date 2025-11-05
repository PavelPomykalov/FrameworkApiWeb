pipeline {
    agent any

    options {
        // Всегда сохранять логи даже при ошибках
        skipDefaultCheckout()
        timestamps()
    }

    parameters {
        booleanParam(name: 'API', defaultValue: false, description: 'Тег API')
        booleanParam(name: 'SMOKE', defaultValue: false, description: 'Тег SMOKE')
        booleanParam(name: 'WEB', defaultValue: false, description: 'Тег WEB')
        booleanParam(name: 'UI', defaultValue: false, description: 'Тег UI')
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir() // Полностью очищаем рабочую директорию
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
                    def selectedTags = []
                    if (params.API) selectedTags << 'API'
                    if (params.SMOKE) selectedTags << 'SMOKE'
                    if (params.WEB) selectedTags << 'WEB'
                    if (params.UI) selectedTags << 'UI'

                    if (selectedTags.isEmpty()) {
                        echo "Теги не выбраны. Тесты не запускаются."
                    } else {
                        def tagsString = selectedTags.join(',')
                        echo "Запуск тестов с тегами: ${tagsString}"

                        // Чистим старые результаты Allure
                        sh 'rm -rf build/allure-results'

                        // Запуск Gradle с выбранными тегами
                        sh "./gradlew clean test -Dallure.results.directory=build/allure-results -Dtags=${tagsString}"
                    }
                }
            }
        }

        stage('Allure Report') {
            steps {
                // Всегда пытаемся собрать Allure, даже если тесты упали
                allure includeProperties: false, reportBuildPolicy: 'ALWAYS', results: [[path: 'build/allure-results']]
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished'
        }
    }
}
