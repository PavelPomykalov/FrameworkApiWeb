pipeline {
    agent any
    parameters {
        booleanParam(name: 'API', defaultValue: true, description: 'Тег API')
        booleanParam(name: 'SMOKE', defaultValue: true, description: 'Тег SMOKE')
        booleanParam(name: 'WEB', defaultValue: true, description: 'Тег WEB')
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

                    def tagsString = selectedTags.join(',')
                    echo "Запуск тестов с тегами: ${tagsString} на стенде dev"
                    sh "./gradlew clean test -Dallure.results.directory=build/allure-results -Denv=dev -Dtags=${tagsString}"
                }
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
            }
        }
    }
}
