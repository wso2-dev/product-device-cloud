/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.iot.integration.ui.pages.groups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wso2.iot.integration.ui.pages.UIUtils;
import org.wso2.iot.integration.ui.pages.UIElementMapper;

import java.io.IOException;
import java.util.List;

/**
 * This class represents the Groups page.
 */
public class DeviceGroupsPage {

    private WebDriver driver;
    private UIElementMapper uiElementMapper;

    public DeviceGroupsPage(WebDriver driver) throws IOException {
        this.driver = driver;
        this.uiElementMapper = UIElementMapper.getInstance();
        WebDriverWait webDriverWait = new WebDriverWait(driver, UIUtils.webDriverTimeOut);

        if (!webDriverWait.until(ExpectedConditions.titleContains(uiElementMapper.getElement("cdmf.groups.page")))) {
            throw new IllegalStateException("This is not the Groups page");
        }
    }

    /**
     * Method to go to the Add device group page, by clicking the Add group button.
     * @return : Add device groups page.
     */
    public DeviceAddGroupPage addNewGroup() throws IOException {
        WebElement addNewGroupBtn = driver.findElement(By.xpath(
                uiElementMapper.getElement("iot.device.viewGroup.empty.addGroup.xpath")));
        addNewGroupBtn.click();
        return new DeviceAddGroupPage(driver);
    }

    /**
     * This method checks whether the created group is visible in the UI.
     * @param groupName : Name of the group created.
     * @return : True if the group is visible. False otherwise.
     */
    public boolean isGroupCreated(String groupName) {
        WebElement table = driver.findElement(By.xpath(uiElementMapper.getElement("iot.device.table.id")));
        List<WebElement> allGroupNames = table.findElements(By.tagName("td"));

        for (WebElement name : allGroupNames) {
            if (name.getText().contains(groupName)){
                return true;
            }
        }
        return false;
    }
}
