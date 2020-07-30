package com.thomas.mySeleniumTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.adapter.FluentTest;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thomas.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MySeleniumTest extends FluentTest {
  @Value("${local.server.port}")
  private int serverPort;
  private WebDriver webDriver = new PhantomJSDriver();
  
  @Before
  public void setUp() {
	  //hete it's possible to clean repos/bdd, reset stuff between tests
  }
  
  private String getUrl() {
    return "http://localhost:" + serverPort;
  }
  
  @Test
  public void isPageShowingDefaultText() {
    goTo(getUrl());
    assertThat(find("#myTextContainer").getText()).isEqualTo("default text");
  }

  @Test
  public void isPageShowingNewTextAfterUserClicksTheButton() {
	  //arrange
	  goTo(getUrl());
	  //act
	  await().atMost(1, TimeUnit.SECONDS);
	  find("#myButton").click();
	  await().atMost(3, TimeUnit.SECONDS);
	  //assert
	  assertThat(find("#myTextContainer").getText()).isEqualTo("Hello from java layer");
  }
  
  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }
}
