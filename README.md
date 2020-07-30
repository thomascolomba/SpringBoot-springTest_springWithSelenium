Run integration tests with Selenium (from front-end in html/js/css to java controller).<br/>
The application is a very simple single-page.<br/>
The application shows a default message. When the user clicks the button, a new message is loaded from the java back-end and displayed.<br/>
<br/>
compile & execute :<br/>
mvn spring-boot:run<br/>

during the execution, browse : http://localhost:8080/<br/>
<br/>
Note : You need to install phantomjs to run the tests.<br/>
<br/>
--HelloController.java<br/>
@RequestMapping("/showMessage")<br/>
...<br/>
@RequestMapping(method = RequestMethod.GET)<br/>
&nbsp;&nbsp;public String sendHelloMessage() {<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<b>return "Hello from java layer";</b><br/>
--index.html<br/>
&lt;button type="button" <b>id="myButton"</b>&gt;Click me to show message&lt;/button&gt;<br/>
&lt;span <b>id="myTextContainer"</b>&gt;default text&lt;/span&gt;<br/>
...<br/>
<b>$("#myButton").click(function(){<br/>
&nbsp;&nbsp;$.get("showMessage", function(data, status){<br/>
&nbsp;&nbsp;&nbsp;&nbsp;$("#myTextContainer").text(data);</b><br/>
--MySeleniumTest.java<br/>
<b>private WebDriver webDriver = new PhantomJSDriver();</b><br/>
...<br/>
@Test<br/>
public void isPageShowingDefaultText() {<br/>
&nbsp;&nbsp;<b>goTo(getUrl());<br/>
&nbsp;&nbsp;assertThat(find("#myTextContainer").getText()).isEqualTo("default text");</b><br/>
<br/>
@Test<br/>
public void isPageShowingNewTextAfterUserClicksTheButton() {<br/>
&nbsp;&nbsp;//arrange<br/>
&nbsp;&nbsp;goTo(getUrl());<br/>
&nbsp;&nbsp;//act<br/>
&nbsp;&nbsp;find("#myButton").click();<br/>
&nbsp;&nbsp;await().atMost(3, TimeUnit.SECONDS);<br/>
&nbsp;&nbsp;//assert<br/>
&nbsp;&nbsp;<b>assertThat(find("#myTextContainer").getText()).isEqualTo("Hello from java layer");</b><br/>
<br/>