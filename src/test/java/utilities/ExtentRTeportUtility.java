package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentRTeportUtility implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// Date format
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report in the
		// project

		sparkReporter.config().setDocumentTitle("Opencart Automation report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setReportName("Anil");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Coustomer");
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("Enivironment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Opersting System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedgroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedgroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedgroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "Execution Succesful");

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getClass().getName());// Creates new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " Got failed");
		test.log(Status.INFO, "Test case FAILED cause: " + result.getThrowable());

		try {
			String imgpath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " Got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();

		// to open the report automatically
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);

		try {
		Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Code to send mail after test execution Automatically
		/*
		 * try { URL url = new URL("file:///" + System.getProperty("user.dir") +
		 * "\\reports\\" + repName);
		 * 
		 * // Create the email message ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp.googleemail.com"); email.setSmtpPort(465);
		 * email.setAuthenticator(new DefaultAuthenticator("singles.club1111@gmail.com",
		 * "password"));// Sender // credentials email.setSSLOnConnect(true);
		 * email.setFrom("singles.club1111@gmail.com");// sender
		 * email.setSubject("Test Result");
		 * email.setMsg("Please find attached report...");
		 * email.addTo("anil.kcv20@gmail.com");// Receiver email.attach(url,
		 * "extent report", "please check report");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

}
