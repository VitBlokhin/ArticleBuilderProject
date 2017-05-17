package org.itstep.pps2701.blokhin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 15.05.2017.
 */
public class ArticleBuilder extends Builder{

    public ArticleBuilder() {
        article = new Article();
    }

    public void parseHeader(String[] text){
        article.setHeading(text[0]);

    } // parseHeader

    public void parseAuthors(String[] text){
        try {
            article.setAuthors(text[1]);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    } // parseAuthors

    public void parseHash(String[] text){
        article.setHash(Integer.parseInt(text[2]));
    } // parseHash

    public void parseText(String[] text){
        String resText = "";
        for(int i = 3; i < text.length; i++){
            resText = resText.concat(text[i] + "\n");
        }
        article.setText(resText);
    } // parseText

    public boolean isCorrectHash(){
        return article.getHash() == article.getText().hashCode();
    } // isCorrectHash

    public void reHashText(){
        article.setHash(article.getText().hashCode());
    } // reHashText


} // class ArticleBuilder
