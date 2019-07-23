//package com.self.fb.testapp.service;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import twitter4j.*;
//import twitter4j.conf.ConfigurationBuilder;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//

//public class twitterFeed {
//    public static void main(String[] args) throws TwitterException {
//        int count = 0;
//        int columnCount = 0;
//        int rowCount = 0;
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("Java Books");
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//
//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("")
//                .setOAuthConsumerSecret("")
//                .setOAuthAccessToken("")
//                .setOAuthAccessTokenSecret("");
//
//        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
//        Query query = new Query("#wall");
//        long lastID = Long.MAX_VALUE;
//        int numberOfTweets = 300;
////        System.out.println(numberOfTweets);
//        ArrayList<Status> tweets = new ArrayList<Status>();
//        while (tweets.size() < numberOfTweets) {
//            if (numberOfTweets - tweets.size() > 100)
//                query.setCount(100);
//            else
//                query.setCount(numberOfTweets - tweets.size());
//            QueryResult result = twitter.search(query);
//            tweets.addAll(result.getTweets());
//            System.out.println("Gathered " + tweets.size() + " tweets");
//
//            for (int i = 0; i < tweets.size(); i++) {
//                Status t = (Status) tweets.get(i);
//                Row row = sheet.createRow(rowCount++);
//                Cell userName = row.createCell(columnCount++);
//                userName.setCellValue(t.getUser().getScreenName());
//                Cell userTweet = row.createCell(columnCount++);
//                userTweet.setCellValue(t.getText());
//                Cell userLocation = row.createCell(columnCount++);
//                userLocation.setCellValue(t.getUser().getLocation());
//                Cell tweetDate = row.createCell(columnCount);
//                tweetDate.setCellValue(t.getCreatedAt());
//                columnCount = 0;
//            }
//
//            try {
//                FileOutputStream outputStream = new FileOutputStream(new File("twitter5.csv"));
//                workbook.write(outputStream);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
