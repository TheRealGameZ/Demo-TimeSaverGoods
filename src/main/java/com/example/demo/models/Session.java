package com.example.demo.models;

public class Session 
{
    private static String username; 
    private static String password;
    private static String cartid;
    private static String checkoutSession;

    
    public Session(String username, String password) 
    {
        Session.setUsername(username);
        Session.setPassword(password);
    }

    public static String getUsername() {
        return username;
    }
    public static void setUsername(String username) {
        Session.username = username;
    }
    public static String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
        Session.password = password;
    }
    public static String getCartid() {
        return cartid;
    }
    public static void setCartid(String cartid) {
        Session.cartid = cartid;
    }
    public static String getCheckoutSession() {
        return checkoutSession;
    }
    public static void setCheckoutSession(String checkoutSession) {
        Session.checkoutSession = checkoutSession;
    }
}
