package com.example.sunrinhack2019.Auth;

public class UserModel {

    //유저 정보를 담을 양식 클래스

    //유저 정보 저장에 필요한 변수 선언
    private String nickname, email, userkey;

    public UserModel(){}

    public UserModel(String nickname, String email, String userkey){ //클래스 양식 지정
        this.nickname = nickname;
        this.email = email;
        this.userkey = userkey;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }






}
