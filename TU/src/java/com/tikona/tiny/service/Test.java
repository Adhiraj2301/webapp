/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikona.tiny.service;

/**
 *
 * @author rahul.yadav
 */
public class Test {
    
    public static void main(String[] args) {
        
        URLShortener u=new URLShortener();
      String r=u.convertToShort("https://www.amazon.in/ap/signin?_encoding=UTF8&openid.assoc_handle=inflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fcard%3Fie%3DUTF8%26ref_%3Dcust_rec_intestitial_signin",1,"A","B");
        System.out.println(""+r);
    }
}
