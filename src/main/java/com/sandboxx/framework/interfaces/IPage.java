package com.sandboxx.framework.interfaces;

import com.sandboxx.dataManagement.constants.GoTo;

public interface IPage {
    boolean isAt();
    void waitForPage();
    void navigateTo(GoTo goTo);
}
