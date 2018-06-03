package com.example.cy.note;



public class unit_item {
    private  String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int update;
    private int delete;
    private String content;
    private String time;

    public unit_item(String id,String title, int update, int delete, String time,String content) {
        this.id=id;
        this.title = title;
        this.update = update;
        this.delete = delete;
        this.time = time;
        this.content=content;
    }
/*列表*/
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
