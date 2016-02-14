package org.wso2.iot.integration.ui.pages.uesr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wso2.iot.integration.ui.pages.UIElementMapper;

/**
 * This class represents the Edit user page of the IOT server
 */
public class EditUserPage {
    private static final Log log = LogFactory.getLog(EditUserPage.class);
    private WebDriver driver;
    private UIElementMapper uiElementMapper;

    public EditUserPage(WebDriver driver) throws Exception {
        this.driver = driver;
        this.uiElementMapper = UIElementMapper.getInstance();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        if (!webDriverWait.until(ExpectedConditions.titleContains("User Management | IoT Server"))){
            throw new IllegalStateException("This is not the Edit User page");
        }
    }

    public void editUser(String password, String firstName, String lastName) {
        driver.findElement(By.xpath(uiElementMapper.getElement("iot.user.add.input.password.xpath")));
    }

}
