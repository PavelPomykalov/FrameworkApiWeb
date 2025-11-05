pipeline {
    agent any
    parameters {
        booleanParam(name: 'API', defaultValue: false, description: 'Тег API')
        booleanParam(name: 'SMOKE', defaultValue: false, description: 'Тег SMOKE')
        booleanParam(name: 'WEB', defaultValue: false, description: 'Тег WEB')
        booleanParam(name: 'UI', defaultValue: false, description: 'Тег UI')
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
                    def selectedTags = []
                    if (params.API) selectedTags << 'API'
                    if (params.SMOKE) selectedTags << 'SMOKE'
                    if (params.WEB) selectedTags << 'WEB'
                    if (params.UI) selectedTags << 'UI'

                    if (selectedTags.isEmpty()) {
                        echo "Ни один тег не выбран. Тесты не будут запущены."
                        currentBuild.result = 'SUCCESS'
                        return
                    }

                    def tagsString = selectedTags.join(',')
                    echo "Запуск тестов с тегами: ${tagsString}"

                    // Gradle с флагом, чтобы Allure сохранял результаты
                    sh "./gradlew clean testWithTags -Dtags=${tagsString} -Dallure.results.directory=build/allure-results || true"
                }
            }
            post {
                always {
                    echo "Генерация Allure-отчета"
                    allure([
                        includeProperties: false,
                        jdk: '',
                        results: [[path: 'build/allure-results']]
                    ])
                }
            }
        }
    }
}
