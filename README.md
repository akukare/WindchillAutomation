# Playwright Framework Template

This is a modular Playwright framework using TypeScript, Page Object Model (POM), JSON-based locators, Allure reporting, and Winston logging.

## Folder Structure

- `src/base/BasePage.ts`: Base class with Playwright essentials.
- `src/pages/HomePage.ts`: Page Object using locators from JSON.
- `src/locators/homePage.json`: JSON file with locators.
- `src/tests/homePage.test.ts`: Sample test.
- `src/utils/logger.ts`: Winston logger setup.
- `playwright.config.ts`: Playwright configuration with Allure.

## Setup

1. Install dependencies:
   ```bash
   Run projectSetup.bat file
   ```
2. Start Execution
   ```bash
   npm run test
   ```
3. Debugging

   To enhance debugging and error analysis, we have enabled the following options in the Playwright framework:

   1. **Take Screenshot**: This option captures a screenshot at the point of failure.
   2. **Traces on First Retry**: This option records traces during the first retry of a failed test, can be viewed on [Trace Viewer](https://trace.playwright.dev/)

   These features help in identifying issues more efficiently by providing visual and trace data.

## Useful options
   Refer documentation links
   1. [Playwright](https://playwright.dev/)
   2. [Typescript](https://www.typescriptlang.org/docs/)

 