package com.self.fb.testapp.service;

import facebook4j.*;
import facebook4j.conf.ConfigurationBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class facebookFeedExtract {


    @PostConstruct
    public void getFeed() throws FacebookException, IOException, URISyntaxException {
        JsonReader rdr;
        int commentsSize = 0;
        /* Configuration for facebook posts with pagination */

        String[] products = new String[]{"Beauty", "iphone", "deodorant"};
        String productname = "";
        ConfigurationBuilder cb = new ConfigurationBuilder();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("wall");
        cb.setDebugEnabled(true)
                .setOAuthAppId("")
                .setOAuthAppSecret("")
                .setOAuthAccessToken("")
                .setOAuthPermissions("read_stream,public_profile,user_friends");

        FacebookFactory ff = new FacebookFactory(cb.build());
        facebook4j.Facebook facebook = ff.getInstance();
        List<facebook4j.Comment> totalComments = new ArrayList<>();

        Reading reading = new Reading().limit(100).fields("comments").until("25-10-2020");
        ResponseList<facebook4j.Post> posts = facebook.getPosts("wall", reading);

        for (facebook4j.Post post : posts) {
            List<facebook4j.Comment> comments = post.getComments();
            totalComments.addAll(comments);
        }

        /*
        BufferedWriter bf = new BufferedWriter( new OutputStreamWriter(new FileOutputStream("facebookLimits2000.csv"),"UTF-8"));
        List<String> words = new ArrayList<>();
        for (Comment comment : totalComments){

            Optional<String> product = Arrays.stream(products).filter(comment.getMessage()::contains).findAny();

            if(product.isPresent()){
                productname = product.get().toString();
            }
            String cmnt = comment.getMessage().replace("\n"," ");
            StringBuffer oneLine= new StringBuffer();

            oneLine.append(comment.getFrom().getId()+",");
            oneLine.append(comment.getFrom().getName()+",");
            oneLine.append(comment.getCreatedTime()+",");
            oneLine.append(cmnt + ",");
            oneLine.append(productname);
            bf.write(oneLine.toString());

            bf.newLine();
        }
        bf.flush();
        bf.close();
*/
        int columnCount = 0;
        int rowCount = 0;
        for (Comment comment : totalComments){
            Optional<String> product = Arrays.stream(products).filter(comment.getMessage()::contains).findAny();

            if(product.isPresent()){
                productname = product.get().toString();
            }

            Row row = sheet.createRow(rowCount++);
            String cmnt = comment.getMessage().replace("\n"," ");
            Cell userId = row.createCell(columnCount++);
            userId.setCellValue((comment.getFrom().getId()));
            Cell userName = row.createCell(columnCount++);
            userName.setCellValue(comment.getFrom().getName());
            Cell message = row.createCell(columnCount++);
            message.setCellValue(cmnt);
            Cell productInComment = row.createCell(columnCount);
            productInComment.setCellValue(productname);
            columnCount = 0;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(new File("facebook.csv"));
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
