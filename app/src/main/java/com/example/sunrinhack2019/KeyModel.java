package com.example.sunrinhack2019;

public class KeyModel {

    //유저 정보를 담을 양식 클래스

    //유저 정보 저장에 필요한 변수 선언
    private String title, info, password, img, date, time, uid, kid;
    private int count;

    public KeyModel(){}

    public KeyModel(String title, String info, String password, String img, String date, String time, int count, String kid){ //클래스 양식 지정
        this.title = title;
        this.info = info;
        this.password = password;
        this.img = img;
        this.date = date;
        this.time = time;
        this.count = count;
        this.kid = kid;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }
//Getter & Setter


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
