import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import internal.GlobalVariable as GlobalVariable

Map<String, String> suiteProperties = new HashMap<String, String>();


suiteProperties.put('id', 'Test Suites/full_test')

suiteProperties.put('name', 'full_test')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())



RunConfiguration.setExecutionSettingFile("C:\\dev\\jee\\SMB\\SMBKatalon\\Reports\\full_test\\20190425_095444\\execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/full_test', suiteProperties, [new TestCaseBinding('Test Cases/create_board_modo', 'Test Cases/create_board_modo',  null), new TestCaseBinding('Test Cases/edit_board_modo', 'Test Cases/edit_board_modo',  null), new TestCaseBinding('Test Cases/create_post_user', 'Test Cases/create_post_user',  null), new TestCaseBinding('Test Cases/create_comment_user', 'Test Cases/create_comment_user',  null), new TestCaseBinding('Test Cases/delete_comment_user', 'Test Cases/delete_comment_user',  null), new TestCaseBinding('Test Cases/delete_post_user', 'Test Cases/delete_post_user',  null), new TestCaseBinding('Test Cases/delete_board_modo', 'Test Cases/delete_board_modo',  null)])
