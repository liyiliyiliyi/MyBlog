package model;

public class Tag {

    private int article_id;
    private int id;
    private String tag;

    @Override
    public String toString() {
        return "Tag [id=" + id + ", tag=" + tag + "]";
    }

    public Tag() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int id) {
        this.id = article_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
