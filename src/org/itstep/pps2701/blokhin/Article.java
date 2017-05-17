package org.itstep.pps2701.blokhin;

import java.io.FileWriter;
import java.security.InvalidParameterException;

/**
 * Created by Vit on 15.05.2017.
 */
public class Article {
    private String heading = "";
    private String authors = "";
    private int hash = 0;
    private String text = "";

    public Article() {
    }

    public Article(String heading, String authors, int hash, String text) {
        this.heading = heading;
        this.authors = authors;
        this.hash = hash;
        this.text = text;
    } // Article

    public String getHeading() {
        return heading;
    }
    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getAuthors() {
        return authors;
    }
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getHash() {
        return hash;
    }
    public void setHash(int hash) {
        this.hash = hash;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("<article>" +
                        "\n<heading>%s</heading>" +
                        "\n<authors>%s</authors>" +
                        "\n<hash>%d</hash>" +
                        "\n<text>%s</text>" +
                        "\n</article>",
                heading, authors, hash, text);
    } // toString

} // class Article
