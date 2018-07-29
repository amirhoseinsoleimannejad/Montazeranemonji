package com.example.amhso.montazeranemonji.otherclass;

public class Item {

    private String uAvatar;
//    private String uName;
    private String uMessage;
//    private String uTime;

    public Item(String uAvatar, String uName, String uMessage, String uTime) {
        this.uAvatar = uAvatar;
//        this.uName = uName;
        this.uMessage = uMessage;
//        this.uTime = uTime;
    }

    public String getuAvatar() {
        return uAvatar;
    }

    public void setuAvatar(String uAvatar) {
        this.uAvatar = uAvatar;
    }

//    public String getuName() {
//        return uName;
//    }

//    public void setuName(String uName) {
//        this.uName = uName;
//    }

    public String getuMessage() {
        return uMessage;
    }

    public void setuMessage(String uMessage) {
        this.uMessage = uMessage;
    }

//    public String getuTime() {
////        return uTime;
////    }

//    public void setuTime(String uTime) {
//        this.uTime = uTime;
//    }

}