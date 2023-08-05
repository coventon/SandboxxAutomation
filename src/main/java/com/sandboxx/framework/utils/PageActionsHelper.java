package com.sandboxx.framework.utils;

import com.google.common.collect.ImmutableList;
import com.sandboxx.framework.base.AppDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.List;

public class PageActionsHelper {
    public enum ScrollDirection {
        UP, DOWN, RIGHT, LEFT
    }

    public static void waitAndClick(By by) {
        // Wait strategy
        AppDriver.getDriver().findElement(by).click();
    }

    public static void waitAndClick(By androidBy, By iosBy) {
        // Wait strategy
        By byBasedOnMobilePlatform = getByBasedOnMobilePlatform(androidBy, iosBy);
        AppDriver.getDriver().findElement(byBasedOnMobilePlatform).click();
    }

    public static void waitAndSendKeys(By by, String value) {
        // Wait Strategy
        AppDriver.getDriver().findElement(by).sendKeys(value);
    }

    public static void selectBy(By by, Consumer<Select> consumer) {
        consumer.accept(new Select(AppDriver.getDriver().findElement(by)));
    }

    public static String getAttribute(By by, Function<WebElement, String> attributeFunction) {
        return attributeFunction.apply(AppDriver.getDriver().findElement(by));
    }

    public static boolean isPresent(By by, Predicate<WebElement> elementPredicate) {
        return elementPredicate.test(AppDriver.getDriver().findElement(by));
    }

    private static By getByBasedOnMobilePlatform(By androidBy, By iosBy) {
        return isAndroid() ? androidBy : iosBy;
    }

    private static boolean isAndroid() {
        return AppDriver.getDriver() instanceof AndroidDriver;
    }

    public static void scrollMobile(By by) {
        String previousPageSource = "";
        while (isElementNotEnabled(by) && isNotEndOfPage(previousPageSource)) {
            previousPageSource = AppDriver.getDriver().getPageSource();
            performScroll();
        }
    }

    public static void scrollMobile(WebElement element) {
        String previosPageSource = "";
        while (isElementNotEnabled(element) && isNotEndOfPage(previosPageSource)) {
            previosPageSource = AppDriver.getDriver().getPageSource();
            performScroll();
        }
    }

    private static boolean isNotEndOfPage(String previousPageSource) {
        return !previousPageSource.equals(AppDriver.getDriver().getPageSource());
    }

    private static boolean isElementNotEnabled(WebElement element) {
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    private static boolean isElementNotEnabled(By by) {
        List<WebElement> elements = AppDriver.getDriver().findElements(by);
        if (isAndroid()) {
            return elements.isEmpty();
        }
        if (!elements.isEmpty()) {
            return elements.get(0).getAttribute("visible").equalsIgnoreCase("false");
        }
        return true;
    }

    private static void performScroll() {
        Dimension size = AppDriver.getDriver().manage().window().getSize();
        System.out.printf("================== Screen size %s ========================", size.toString());
        int startX = size.getWidth() / 2;
        int endX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endY = (int) (size.getHeight() * 0.25);

        performScrollUsingSequence(startX, startY, endX, endY);
    }

    private static void performScrollUsingSequence(int startX, int startY, int endX, int endY) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger_1");
        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY)) // Create coordinates
                .addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg())) // Put finger down to point
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY)) // Move finger to the End coordinates
                .addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg())); // Lift finger up
    }

    // ## Swipe / Scroll method with Point
    public static void scrollDown() {
        Dimension size = AppDriver.getDriver().manage().window().getSize();
        System.out.println("===> Screen Size: " + size + "<===");
        System.out.println(">>> Scrolling Down");
        Point midpoint = new Point((int) (size.getWidth() * 0.5), (int) (size.getHeight() * 0.5));
        int bottom = midpoint.y + (int) (midpoint.y * 0.75);
        int top = midpoint.y - (int) (midpoint.y * 0.75);

        Point start = new Point(midpoint.x, bottom);
        Point end = new Point(midpoint.x, top);

        swipe(start, end, Duration.ofMillis(500));
    }

    public static void scrollUp() {
        Dimension size = AppDriver.getDriver().manage().window().getSize();
        System.out.println("===> Screen Size: " + size + "<===");
        System.out.println(">>> Scrolling Up");
        Point midpoint = new Point((int) (size.getWidth() * 0.5), (int) (size.getHeight() * 0.5));
        int bottom = midpoint.y + (int) (midpoint.y * 0.75);
        int top = midpoint.y - (int) (midpoint.y * 0.75);

        Point end = new Point(midpoint.x,bottom);
        Point start = new Point(midpoint.x,top);

        swipe(start,end,Duration.ofMillis(500));
    }

    public static void swipeRight(){
        Dimension size = AppDriver.getDriver().manage().window().getSize();
        System.out.println("===> Screen Size: " + size + "<===");
        System.out.println(">>> Swiping Right");
        Point midpoint = new Point((int)(size.getWidth()*0.5),(int)(size.getHeight()*0.5));
        int right = midpoint.x + (int)(midpoint.x * 0.75);
        int left = midpoint.x - (int) (midpoint.x * 0.75);

        Point start = new Point(left,midpoint.y);
        Point end = new Point(right,midpoint.y);

        swipe(start,end,Duration.ofMillis(500));
    }

    public static void swipeLeft(){
        Dimension size = AppDriver.getDriver().manage().window().getSize();
        System.out.println("===> Screen Size: " + size + "<===");
        System.out.println(">>> Swiping Left");
        Point midpoint = new Point((int)(size.getWidth()*0.5),(int)(size.getHeight()*0.5));
        int right = midpoint.x + (int)(midpoint.x * 0.75);
        int left = midpoint.x - (int) (midpoint.x * 0.75);

        Point start = new Point(right,midpoint.y);
        Point end = new Point(left,midpoint.y);

        swipe(start,end,Duration.ofMillis(500));
    }

    public static void scroll(ScrollDirection direction,double scrollRatio){
        Duration SCROLL_DURATION = Duration.ofMillis(400);
        if(scrollRatio <0 || scrollRatio > 1){
            throw new Error(">>> Scroll ratio must be between 0 and 1");
        }

        Dimension size = AppDriver.getDriver().manage().window().getSize();
        System.out.println("===> Screen Size: " + size + "<===");
        System.out.println(">>> Swiping " + direction.toString());

        Point midpoint = new Point((int) (size.getWidth() * 0.5), (int) (size.getHeight() * 0.5));
        int right = midpoint.x + (int)(midpoint.x * scrollRatio);
        int left = midpoint.x - (int)(midpoint.x * scrollRatio);
        int bottom = midpoint.y + (int)(midpoint.y * scrollRatio);
        int top = midpoint.y  = (int)(midpoint.y * scrollRatio);

        if(direction == ScrollDirection.UP){
            swipe(new Point(midpoint.x,bottom), new Point(midpoint.x,top),SCROLL_DURATION);
        }else if(direction == ScrollDirection.DOWN){
            swipe(new Point(midpoint.x,top),new Point(midpoint.x,bottom),SCROLL_DURATION);
        }else if(direction == ScrollDirection.RIGHT){
            swipe(new Point(left,midpoint.y),new Point(right,midpoint.y),SCROLL_DURATION);
        }else if(direction == ScrollDirection.LEFT){
            swipe(new Point(right,midpoint.y), new Point(left,midpoint.y),SCROLL_DURATION);
        }
    }

    private static void swipe(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH,"finger1");
        Sequence swipe = new Sequence(input,0);
        swipe.addAction(input.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),start));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration,PointerInput.Origin.viewport(),end));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        ((RemoteWebDriver)AppDriver.getDriver()).perform(ImmutableList.of(swipe));
    }

    public static void click(By byEl){
        new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(byEl)).click();
    }

    public static void sendKeys(By byEl, String text){
        waitForEl(byEl);
        AppDriver.getDriver().findElement(byEl).sendKeys(text);
    }

    public static void waitForEl(By byEl){
        new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(byEl));
    }


}
