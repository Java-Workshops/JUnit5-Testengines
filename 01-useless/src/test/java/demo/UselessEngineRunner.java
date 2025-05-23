package demo;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherConfig;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import com.svenruppert.junit.engine.useless.UselessEngine;

import java.io.PrintWriter;

import static java.lang.System.out;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;
import static org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder.request;

public class UselessEngineRunner {


  public static void main(String[] args) {

    final SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();
    final LauncherConfig config = LauncherConfig.builder()
                                                .enableTestEngineAutoRegistration(false)
                                                .enableTestExecutionListenerAutoRegistration(false)
                                                .addTestEngines(new UselessEngine())
                                                .addTestExecutionListeners(summaryGeneratingListener)
                                                .build();

    Launcher launcher = LauncherFactory.create(config);

    //static import LauncherDiscoveryRequestBuilder
    LauncherDiscoveryRequest request = request().selectors(selectPackage("junit"))
                                                .build();

    TestPlan testPlan = launcher.discover(request);

    launcher.execute(request);

    TestExecutionSummary summary = summaryGeneratingListener.getSummary();
    summary.printTo(new PrintWriter(out));
    testPlan.getRoots()
            .stream()
            .filter(TestIdentifier::isContainer)
            .map(TestIdentifier::getDisplayName)
            .map(name -> "TestEngineName: " + name)
            .toList()
            .forEach(out::println);
  }


}
