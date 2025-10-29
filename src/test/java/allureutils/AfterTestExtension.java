package allureutils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * JUnit 5 расширение, которое автоматически выполняется после завершения каждого теста.
 * Если тест падает (возникает исключение), расширение:
 *     <li>Снимает скриншот текущего состояния браузера и прикладывает его в Allure отчет.</li>
 *     <li>Сохраняет HTML исходный код текущей страницы и прикладывает его в Allure отчет.</li>
 * Это позволяет автоматически собирать артефакты для отладки упавших тестов без необходимости.
 */

    public class AfterTestExtension implements AfterTestExecutionCallback {
        @Override
        public void afterTestExecution(ExtensionContext extensionContext) {
            if (extensionContext.getExecutionException().isPresent()) {
                AllureSteps.screenshot();
                AllureSteps.sourceCode();
            }
        }
    }

