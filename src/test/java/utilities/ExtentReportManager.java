package utilities;

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
import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String reportName;
	
	@Override
	public void onStart(ITestContext context) {
		String os = context.getCurrentXmlTest().getParameter("os");
		String browser = context.getCurrentXmlTest().getParameter("browser");
		List<String> groupsIncluded = context.getCurrentXmlTest().getIncludedGroups();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		reportName = "test-report-" + timeStamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
		sparkReporter.config().setDocumentTitle("OrangeHRM automation report");
		sparkReporter.config().setReportName("OrangeHRM functional testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OrangeHRM");
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("Operating system", os);
		extent.setSystemInfo("Browser", browser);
		
		if(!groupsIncluded.isEmpty())
			extent.setSystemInfo("Included groups", groupsIncluded.toString());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " successfully executed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		try {
			String imagePath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imagePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
		//to open the report automatically in the browser
//		String reportPath = ".\\reports\\" + reportName;
//		File extentReport = new File(reportPath);
//		try {
//			Desktop.getDesktop().browse(extentReport.toURI());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
