pipeline {
    agent any

    parameters {
        booleanParam(name: 'API', defaultValue: false, description: 'Тег API')
        booleanParam(name: 'SMOKE', defaultValue: false, description: 'Тег SMOKE')
        booleanParam(name: 'WEB', defaultValue: false, description: 'Тег WEB')
        booleanParam(name: 'UI', defaultValue: false, description: 'Тег UI')
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir() // чистим рабочую директорию перед сборкой
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
                    // Формируем список задач Gradle для выбранных тегов
                    def tasks = []
                    if (params.API) tasks << 'testApi'
                    if (params.SMOKE) tasks << 'testSmoke'
                    if (params.WEB) tasks << 'testWeb'
                    if (params.UI) tasks << 'testWeb'  // UI-тесты через ту же задачу

                    if (tasks.isEmpty()) {
                        echo "Тесты не выбраны. Пропуск."
                    } else {
                        // удаляем предыдущие результаты Allure
                        sh 'rm -rf build/allure-results'

                        // запускаем выбранные задачи
                        tasks.each { task ->
                            echo "Запуск Gradle задачи: ${task}"
                            // добавляем headless для UI-тестов
                            if (task == 'testWeb') {
                                sh "./gradlew ${task} -Dallure.results.directory=build/allure-results -Dheadless=true || true"
                            } else {
                                sh "./gradlew ${task} -Dallure.results.directory=build/allure-results || true"
                            }
                        }
                    }
                }
            }
        }

        stage('Allure Report') {
            steps {
                // формируем отчёт Allure из результатов
                allure([
                    results: [[path: 'build/allure-results']],
                    reportBuildPolicy: 'ALWAYS'
                ])
            }
        }
    }
}
