package com.sandboxx;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        System.out.println("Hello World!");
        System.out.println(System.getProperty("user.dir"));
        System.out.println("System OS: " + System.getProperty("os.name"));

        System.out.println("---------");
        TestTracker tracker = new TestTracker();

        tracker.startTest();

        // Simulate interval
        try {
            Thread.sleep(83120);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tracker.finishTest();

        String duration = tracker.calculateDuration();
        System.out.println("Duration: " + duration);
    }

    }

     class TestTracker{
        private LocalDateTime testStartTime;
        private LocalDateTime testFinishTime;

        public void startTest(){
            testStartTime = LocalDateTime.now();
        }
        public void finishTest(){
            testFinishTime = LocalDateTime.now();
        }

        public String calculateDuration(){
            Duration duration = Duration.between(testStartTime,testFinishTime);
            long hours = duration.toHours();
            long minutes = duration.toMinutes();
            long seconds = duration.getSeconds();

            StringBuilder durationString = new StringBuilder();
            if(hours > 0){
                durationString.append(hours).append(" hrs ");
            }
            if(minutes > 0 ){
                durationString.append(minutes).append(" min ");
            }
            durationString.append(seconds).append(" sec");

            return durationString.toString();
        }
    }

