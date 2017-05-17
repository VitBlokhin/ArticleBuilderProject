package org.itstep.pps2701.blokhin;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 16.05.2017.
 */

/** 'Director' */
public class ArticleConverter {
    private Builder articleBuilder;
    private Article article;    // Нужно для сохранения статьи в xml-файл
    private String[] text;      // для чтения из файла

    public void setArticleBuilder(Builder articleBuilder) {
        this.articleBuilder = articleBuilder;
    }


    public String getText() {
        String text = "";
        for(String str : this.text){
            text += str.concat("\n");
        }
        return text;
    }

    public void buildArticle(){
        articleBuilder.parseHeader(text);
        articleBuilder.parseAuthors(text);
        articleBuilder.parseHash(text);
        articleBuilder.parseText(text);

        if(!articleBuilder.isCorrectHash()) articleBuilder.reHashText();
    }

    public Article getArticle(){
        article =  articleBuilder.getArticle();

        return article;
    }

    public void loadFile(String fileName) throws FileNotFoundException, IOException{
        if (!Utils.isValidFile(fileName))
            throw new InvalidParameterException(
                    String.format("\"%s\" не является корректным именем файла", fileName));

        List<String> lines = new ArrayList<>();

            String line;
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }

            text = lines.toArray(new String[0]);

    } // loadFile

    public void saveXml(String fileName) throws IOException {

        if(!fileName.endsWith(".xml")) fileName +=".xml";
        FileWriter fw = new FileWriter(fileName);
        fw.write(String.format("<article>" +
                        "\n<heading>%s</heading>" +
                        "\n<authors>%s</authors>" +
                        "\n<hash>%d</hash>" +
                        "\n<text>%s</text>" +
                        "\n</article>",
                article.getHeading(), article.getAuthors(), article.getHash(), article.getText()));

        fw.close();

    } // saveXml

} // class ArticleConverter
