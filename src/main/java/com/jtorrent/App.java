package com.jtorrent;

import com.jtorrent.bencode.*;
import com.jtorrent.tracker.Tracker;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;


public class App
{
       public static void main( String[] args )
            throws InvalidBencodeException, FileNotFoundException,
            IOException, NoSuchAlgorithmException
    {
        File f = new File("/Users/pgeske/Downloads/ubuntu-16.10-desktop-amd64.iso.torrent");
        FileInputStream fs = new FileInputStream(f);
        BenItem bi = BenDecoder.decode(fs);
        Tracker tracker = new Tracker(bi.find("announce"), bi.find("info"), "akdlakdiejsldifjelsk");
        URL request = tracker.getRequest();
        BenItem trackerResponse = tracker.getResponse();
        byte[] compactPeers = trackerResponse.find("peers").getString().getBytes("ISO-8859-1");
        for (int i = 0; i < 4; i++) {
            int x = (compactPeers[i] & 0xFF);
            System.out.println(x);
        }
//        System.out.println(compactPeers);
//        System.out.println(peers);
//        for (String key : peers.keySet()) {
//            System.out.println(key + ": " + peers.get(key));
//        }
    }
}
