package com.sandboxx.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.Objects;

public class MainNavigation implements WrapsElement {

    private By homeButtonLocator = By.xpath("");
    private By profileButtonLocator = By.xpath("");
    private By squadButtonLocator = By.xpath("");
    private By shopButtonLocator = By.xpath("");
    private By newsButtonLocator = By.xpath("");
    private By musterButtonLocator = By.xpath("");
    public WebElement wrappedElement;

    @Override
    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    public WebElement homeButton = wrappedElement.findElement(homeButtonLocator);
    public WebElement profileButton = wrappedElement.findElement(profileButtonLocator);
    public WebElement squadButton = wrappedElement.findElement(squadButtonLocator);
    public WebElement shopButton = wrappedElement.findElement(shopButtonLocator);
    public WebElement newsButton = wrappedElement.findElement(newsButtonLocator);
    public WebElement musterButton = wrappedElement.findElement(musterButtonLocator);


    public MainNavigation(WebElement wrappedElement) {
        if (!wrappedElement.getAttribute("class").equals("main-nav")) {
            throw new UnexpectedTagNameException("mainNav", wrappedElement.getAttribute("class"));
        }
        this.wrappedElement = wrappedElement;
    }


}
