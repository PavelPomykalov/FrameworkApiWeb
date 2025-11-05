pipeline {
    agent any
    parameters {
        extendedChoice(
            name: 'TAGS',
            type: 'PT_CHECKBOX',
            multiSelectDelimiter: ',',
            description: 'Выберите теги тестов',
            quoteValue: false,
            visibleItemCount: 5,
            value: 'API,SMOKE,WEB,UI'
        )
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/PavelPomykalov/FrameworkApiWeb', branch: 'main'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Запуск тестов с тегами: ${params.TAGS} на стенде dev"
                sh "./gradlew clean test -Dallure.results.directory=build/allure-results -Denv=dev -Dtags=${params.TAGS}"
            }
        }

        stage('Allure Report') {
            steps {
                allure results: [[path: 'build/allure-results']]
            }
        }
    }
}
