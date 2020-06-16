package com.asocialfingers.kachestvo.Utils.Connection;

public class ConnectionLinks {
    
    static String ipAddress = "your_ip_address";
    
    public static String previousSlide = "http://" + ipAddress + "/kachestvo/presentation/previous";

    public static String insertComment = "http://" + ipAddress + "/kachestvo/comments/insert";

    public static String nextSlide = "http://" + ipAddress + "/kachestvo/presentation/next";

    public static String getComment = "http://" + ipAddress + "/kachestvo/comments";

    public static String sendMail = "http://" + ipAddress + "/kachestvo/email/send";
}
