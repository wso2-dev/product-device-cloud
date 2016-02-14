/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.wso2.carbon.iot.integration.web.ui.test.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.automation.extensions.selenium.BrowserManager;
import org.wso2.carbon.iot.integration.web.ui.test.Constants;
import org.wso2.carbon.iot.integration.web.ui.test.LoginUtils;
import org.wso2.iot.integration.ui.pages.IOTIntegrationUIBaseTestCase;
import org.wso2.iot.integration.ui.pages.home.IOTAdminDashboard;
import org.wso2.iot.integration.ui.pages.uesr.AddUserPage;
import org.wso2.iot.integration.ui.pages.uesr.UserListingPage;

/**
 * Test for checking admin capabilities.
 *    - Create a new User
 *    - Delete a user
 *    -- and more
 */
public class TestAdminFunctions extends IOTIntegrationUIBaseTestCase {

    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        super.init();
        driver = BrowserManager.getWebDriver();
        LoginUtils.login(driver, automationContext, getWebAppURL());
    }

    @Test(description = "Test for creating a new user")
    public void createUserTest() throws Exception {
        IOTAdminDashboard adminDashboard = new IOTAdminDashboard(driver);
        AddUserPage addUserPage = adminDashboard.addUser();
        addUserPage.createNewUser("user1", "User", "User", "user@wso2.com");
    }

    @Test(description = "Test for deleting a created user", dependsOnMethods = {"createUserTest"})
    public void deleteUserTest() throws Exception {
        driver.get(getWebAppURL() + Constants.IOT_HOME_URL);
        IOTAdminDashboard adminDashboard = new IOTAdminDashboard(driver);
        UserListingPage userListingPage = adminDashboard.viewUser();
        userListingPage.deleteUser();
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
