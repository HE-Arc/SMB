import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('http://panama.internet-box.ch:9090/boards')

WebUI.click(findTestObject('Object Repository/Page_Spring Boot Thymeleaf/a_Login'))

WebUI.setText(findTestObject('Object Repository/Page_Spring Boot Thymeleaf/input_Username_username'), 'lewislewis')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Spring Boot Thymeleaf/input_Password_password'), 'eWrmvIvEgHHUCGRVcsPJcw==')

WebUI.click(findTestObject('Page_Spring Boot Thymeleaf/button_Log In'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_Spring Boot Thymeleaf/a_Boards'))

WebUI.click(findTestObject('Object Repository/Page_Spring Boot Thymeleaf/i_Description_fas fa-plus'))

WebUI.setText(findTestObject('Object Repository/Page_Spring Boot Thymeleaf/input_Board name_name'), 'test')

WebUI.setText(findTestObject('Object Repository/Page_Spring Boot Thymeleaf/textarea_Board description_description'), 'test')

WebUI.click(findTestObject('Object Repository/Page_Spring Boot Thymeleaf/button_Save'))

WebUI.closeBrowser()

