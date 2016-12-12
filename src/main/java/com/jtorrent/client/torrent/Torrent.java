package com.jtorrent.client.torrent;

import com.jtorrent.bencode.BenDecoder;
import com.jtorrent.bencode.BenItem;
import com.jtorrent.bencode.InvalidBencodeException;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;

/**
 * Created by Philip on 12/10/2016.
 * This module acts as a wrapper for a BenItem which represents
 * a torrent's meta info.
 */
public class Torrent {
    private BenItem metaInfo;

    public Torrent(File f) throws IOException, InvalidBencodeException {
        FileInputStream fs = new FileInputStream(f);
        this.metaInfo = BenDecoder.decode(fs);
    }

    public Torrent(String s) throws IOException, InvalidBencodeException {
        this.metaInfo = BenDecoder.decode(s);
    }

    public Torrent(InputStream is) throws IOException, InvalidBencodeException {
        this.metaInfo = BenDecoder.decode(is);
    }

    public String announce() throws InvalidBencodeException {
        return this.metaInfo.find("announce").getString();
    }

    /**
     *  Generates a hexadecimal hash of the info dictionary in the torrent's meta info
     *  using the SHA-1 hash algorithm.
     * @return The hash as a string of hexidecimal numbers
     * @throws InvalidBencodeException
     * @throws UnsupportedEncodingException
     */
    public String infoHash() throws InvalidBencodeException, UnsupportedEncodingException {
        return DigestUtils.sha1Hex(this.metaInfo.find("info").toBencode().getBytes("ISO-8859-1"));

    }
}
