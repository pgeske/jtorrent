package com.jtorrent.client;

import com.jtorrent.bencode.InvalidBencodeException;
import com.jtorrent.bencode.MetaInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pgeske on 10/23/16.
 */
public class Client {
    private MetaInfo metaInfo;

    public Client() {}

    public Client(MetaInfo mi) {
        this.metaInfo = mi;
    }

    /**
     * Uses MetaInfo to construct an HTTP request with parameters to send to the tracker.
     * @return The HTTP request wrapped in a URL object.
     * @throws InvalidBencodeException
     * @throws MalformedURLException
     * @throws NoSuchAlgorithmException
     */
//    public URL getTrackerRequestURL() {
//        String parameters = "?";
//
//    }
//    public URL getTrackerRequest() throws InvalidBencodeException, MalformedURLException, NoSuchAlgorithmException{
//        String parameters = "?";
//        // Append GET parameters
//        MessageDigest md = MessageDigest.getInstance("SHA1");
//        byte[] infoHash = md.digest(this.metaInfo.getInfo().toBencode().getBytes());
//        parameters += "info=" + infoHash;
//        parameters += "&peer_id=" + "pgeske";
//        String request = this.metaInfo.getTracker().getString() + parameters;
//    }


}
