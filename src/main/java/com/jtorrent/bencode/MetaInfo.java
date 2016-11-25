package com.jtorrent.bencode;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Created by philip on 10/19/16.
 */
public class MetaInfo {
    private BenItem root;

    public MetaInfo(BenItem bi) throws InvalidBencodeException {
        if (bi.getType() != BenType.B_DICT) {
            throw new InvalidBencodeException("The root of a torrent file must be a dictionary.");
        }
        this.root = bi;
    }

    public BenItem getTracker() throws InvalidBencodeException {
        return root.find("announce");
    }

    public BenItem getInfo() throws InvalidBencodeException {
        return root.find("info");
    }

    public byte[] getInfoHash() throws NoSuchAlgorithmException, InvalidBencodeException, UnsupportedEncodingException{
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        String infoBencode = root.find("info").toBencode();
        md.update(infoBencode.getBytes("UTF-8"));
        return md.digest();
    }
}
