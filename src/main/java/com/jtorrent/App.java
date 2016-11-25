package com.jtorrent;

import com.jtorrent.bencode.*;
import com.jtorrent.tracker.Tracker;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.Formatter;


public class App
{
       public static void main( String[] args )
            throws InvalidBencodeException, FileNotFoundException,
            IOException, NoSuchAlgorithmException
    {
        File f = new File("/Users/pgeske/Downloads/ubuntu-16.10-desktop-amd64.iso.torrent");
        FileInputStream fs = new FileInputStream(f);
        BenItem bi = BenDecoder.decode(fs);
        String infoHash = DigestUtils.sha1Hex(bi.find("info").toBencode().getBytes("ISO-8859-1"));
        System.out.println(bi.find("announce").getString());
        System.out.println(infoHash);
//        System.out.println(URLEncoder.encode(new String(infoHash, "ISO-8859-1")));
//        System.out.println(DigestUtils.sha1Hex(bi.find("info").toBencode().getBytes("ISO-8859-1")));
//        byte[] torrent = new byte[(int) f.length()];
//        fs.read(torrent);
//        String infoString = new String(torrent, "ISO-8859-1");
//        infoString = infoString.substring(infoString.indexOf("info") + 4, infoString.length() - 1);
//        System.out.println(DigestUtils.sha1Hex(infoString.getBytes("ISO-8859-1")));
//        System.out.println(infoString.charAt(infoString.length() - 1));







//        String info = "";
////        BufferedReader in = new BufferedReader(new InputStreamReader(fs, "UTF-8"));
////        String str;
////        while ((str = in.readLine()) != null) {
////            info += str;
////        }
//        int i;
//        info = info.substring(info.indexOf("info") + 4, info.length() - 1);
//        System.out.println(info);
//        FileInputStream fs1 = new FileInputStream(f);
//        BenItem bi = BenDecoder.decode(fs1);
//        String otherInfo = bi.find("info").toBencode();
//        System.out.println(DigestUtils.sha1Hex(info));
//        System.out.println(DigestUtils.sha1Hex(otherInfo.getBytes("UTF-8")));


//
//
//        Tracker tracker = new Tracker(bi.find("announce"), bi.find("info"), "-AZ2060-");
//        URL request = tracker.getRequest();
//        System.out.println(request);
//        System.out.println(tracker.getResponse().getString());
    }
}
