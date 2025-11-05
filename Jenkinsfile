pipeline {
    agent any

    parameters {
        booleanParam(name: 'API', defaultValue: false, description: 'Запуск тестов с тегом API')
        booleanParam(name: 'SMOKE', defaultValue: false, description: 'Запуск тестов с тегом SMOKE')
        booleanParam(name: 'WEB', defaultValue: false, description: 'Запуск тестов с тегом WEB')
        booleanParam(name: 'UI', defaultValue: false, description: 'Запуск тестов с тегом UI')
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/PavelPomykalov/FrameworkApiWeb', branch: 'main'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def tags = []
                    if (params.API) tags << 'API'
                    if (params.SMOKE) tags << 'SMOKE'
                    if (params.WEB) tags << 'WEB'
                    if (params.UI) tags << 'UI'

                    if (tags.isEmpty()) {
                        echo "Ни один тест не выбран. Пропускаем запуск."
                    } else {
                        echo "Запуск тестов с тегами: ${tags.join(', ')}"
                        sh "rm -rf build/allure-results"
                        // Запуск Gradle с тегами
                        sh "./gradlew clean test -Dallure.results.directory=build/allure-results -Dtags=${tags.join(',')} || true"
                        // Генерация Allure отчета
                        sh "./gradlew allureReport || true"
                    }
                }
            }
        }
    }

    post {
        always {
            // Публикация Allure отчета
            allure([
                results: [[path: 'build/allure-results']]
            ])
        }
    }
}
