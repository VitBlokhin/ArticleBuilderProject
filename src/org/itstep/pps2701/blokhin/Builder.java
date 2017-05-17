package org.itstep.pps2701.blokhin;

/**
 * Created by Vit on 15.05.2017.
 */
abstract public class Builder {
    protected Article article;

    public Builder() {
        article = new Article();
    }

    public Article getArticle(){
        return article;
    } // getArticle

    abstract public void parseHeader(String[] text);
    abstract public void parseAuthors(String[] text);
    abstract public void parseHash(String[] text);
    abstract public void parseText(String[] text);
    abstract public boolean isCorrectHash();
    abstract public void reHashText();

} // class Builder
