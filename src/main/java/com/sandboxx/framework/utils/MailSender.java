package com.sandboxx.framework.utils;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class MailSender {

    public static void sendEmailWithReport(TestResultData testResult) {
        // Gmail account credentials
        String senderEmail = " roman.nejouta@sandboxx.us";
        String senderPassword = "oltiowwayvhckufx";

        // Recipient email address
        String recipientEmail = " roman.nejouta@sandboxx.us";

        // Email subject
        String subject = "Automation Test Report";

        // Create the email body with HTML and CSS layout
        String emailBody = generateEmailBody(testResult);

        // File path of the report to be attached
        String reportFilePath = System.getProperty("user.dir") +"/testOutput/TestReport.html";

        try {
            // Set the email properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Create a session with authentication
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            // Create the email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);

            // Create a multipart message to hold the email body and attachment
            Multipart multipart = new MimeMultipart();

            // Create a body part for the email content
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailBody, "text/html");

            // Add the email content to the multipart message
            multipart.addBodyPart(messageBodyPart);

            // Attach the report file to the email
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(new File(reportFilePath));
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName("TestReport.html");

            // Add the attachment to the multipart message
            multipart.addBodyPart(attachmentBodyPart);

            // Set the multipart message as the email content
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println(">>> Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static String generateEmailBody(TestResultData resultData) {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeNow = now.format(formatter);

        // Generate the HTML and CSS layout for the email body
        String emailBody = "<html><head><style>" +
                "table { border-collapse: collapse; }" +
                "th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }" +
                "th { font-size: 1.2em; }" +
                "</style></head><body>" +
                "<h2>Sandboxx Automation Test Report</h2>" +
                "<p><b>Date and Time:</b> " + dateTimeNow + "</p>" +
                "<p>Please find attached report for details</p>" +
                "<table>" +
                "<tr><th colspan=\"2\">Summary</th></tr>" +
                "<tr><td><b>Test Suite</b></td><td>Sandboxx Daily Regression</td></tr>" +
                "<tr><td><b>Platform</b></td><td>"+resultData.getPlatform()+"</td></tr>" +
                "<tr><td><b>Environment</b></td><td>"+resultData.getEnvironment()+"</td></tr>" +
                "<tr><td><b>Start</b></td><td>"+resultData.getStartTime()+"</td></tr>" +
                "<tr><td><b>Finish</b></td><td>"+resultData.getFinishTime()+"</td></tr>" +
                "<tr><td><b>Duration</b></td><td>"+resultData.getDuration()+"</td></tr>" +
                "</table>" +
                "<br>" +
                "<table>" +
                "<tr><th colspan=\"2\">Results</th></tr>" +
                "<tr><td><b>Pass</b></td><td>"+resultData.getTestsPasses()+"</td></tr>" +
                "<tr><td><b>Fail</b></td><td>"+resultData.getTestsFailed()+"</td></tr>" +
                "<tr><td><b>Total</b></td><td>"+resultData.getTotal()+"</td></tr>" +
                "<tr><td><b>Pass Rate</b></td><td>"+resultData.getPassRate()+"</td></tr>" +
                "</table>" +
                "</body></html>";

        return emailBody;
    }



}
