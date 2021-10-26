package com.techproed.javascriptexecutor;

import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.JSUtils;
import com.techproed.utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day16_JSUtilTest {

    @Test
    public void scrollIntoView() {
        Driver.getDriver().get(ConfigReader.getProperty("app_url"));
        WebElement resentBlog = Driver.getDriver().findElement(By.xpath("//*[.='Recent Blog']"));
        JSUtils.scrollIntoVIewJS(resentBlog);
        ReusableMethods.waitFor(3);
        //        What could be the reason waitfor doesn't work before
        // we need to wait AFTER SCROLLING for the TEXT to display properly
        Assert.assertEquals(resentBlog.getText(),"Recent Blog");
        Driver.closeDriver();

    }

    @Test
    public void clickWithJS() {
        Driver.getDriver().get(ConfigReader.getProperty("app_url"));
        WebElement checkAvailabilityButton = Driver.getDriver().findElement(By.xpath("//input[@type='submit']"));
        JSUtils.clickElementByJS(checkAvailabilityButton);
        Driver.closeDriver();
    }

    @Test
    public void flashJS() {
        //hover on button
        Driver.getDriver().get(ConfigReader.getProperty("app_url"));
        WebElement checkAvailabilityButton = Driver.getDriver().findElement(By.xpath("//input[@type='submit']"));
        JSUtils.flash(checkAvailabilityButton);
        Driver.closeDriver();

    }

    @Test
    public void changeColorJS() {
        Driver.getDriver().get(ConfigReader.getProperty("app_url"));
        WebElement checkAvailabilityButton = Driver.getDriver().findElement(By.xpath("//input[@type='submit']"));
        JSUtils.changeColor("red", checkAvailabilityButton);
        Driver.closeDriver();
    }
}
