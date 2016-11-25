package com.jtorrent.tracker;

import com.jtorrent.bencode.BenItem;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * This module acts as an interface to set tracker request parameters,
 * and to communicate with the tracker.
 */
public class Tracker {
    private BenItem announce;
    // Request parameters
    private BenItem info;
    private String peerId;
    private Integer port;
    private Integer uploaded;
    private Integer downloaded;
    private Integer left;
    private Integer compact;
    private String event;



    /**
     * Tracker constructor.
     * @param announce The announce URL of the tracker.
     * @param info The info value in meta info.
     * @param peerId The identifier of the Peer which is using the tracker.
     */
    public Tracker(BenItem announce, BenItem info, String peerId) {
        this.announce = announce;
        this.info = info;
        this.peerId = peerId;
        this.port = 6888;
        this.uploaded = 0;
        this.downloaded = 0;
        this.left = 0;
        this.compact = 1;
        this.event = "started";
    }

    // URL Parameter Setters
    public void setInfo(BenItem info) { this.info = info; }

    public void setPeerId(String peerId) { this.peerId = peerId; }

    public void setPort(Integer port) { this.port = port; }

    public void setUploaded(Integer uploaded) { this.uploaded = uploaded; }

    public void setDownloaded(Integer downloaded) { this.downloaded = downloaded; }

    public void setLeft(Integer left) { this.left = left; }

    public void setCompact(int compact) { this.compact = compact; }

    public void setEvent(String event) { this.event = event; }

    /**
     * Sets all this's request parameters to null.
     */
    public void flush() {
        this.info = null;
        this.peerId = null;
        this.port = null;
        this.uploaded = null;
        this.downloaded = null;
        this.left = null;
        this.compact = null;
        this.event = null;
    }

    /**
     * Constructs and returns a tracker request, using all set tracker parameters.
     * @return The tracker GET request wrapped in a URL.
     */
    public URL getRequest() throws NoSuchAlgorithmException, UnsupportedEncodingException, MalformedURLException{
        String request = "";
        ArrayList<String> parameters = new ArrayList<String>();
        // Add SHA-1 info hash to request
        if (this.info != null) {
            String infoBencode = this.info.toBencode();
//            byte[] infoHash = DigestUtils.sha1(this.info.toBencode().getBytes("ISO-8859-1"));
//=            parameters.add("info_hash=" + URLEncoder.encode(new String(infoHash, "ISO-8859-1")));
        }
        // Append peer id
        if (this.peerId != null) {
            parameters.add("peer_id=" + this.peerId);
        }
        if (this.port != null) {
            parameters.add("port=" + this.port);
        }
        if (this.uploaded != null) {
            parameters.add("uploaded=" + this.uploaded);
        }
        if (this.downloaded != null) {
            parameters.add("downloaded=" + this.downloaded);
        }
        if (this.left != null) {
            parameters.add("left=" + this.left);
        }
        if (this.compact != null) {
            parameters.add("compact=" + this.compact);
        }
        if (this.event != null) {
            parameters.add("event=" + this.event);
        }
        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) {
                request += '&';
            }
            request += parameters.get(i);
        }
        //URL encode the get request
        return new URL(this.announce.getString() + "?" + request);
    }

    public BenItem getResponse()
            throws NoSuchAlgorithmException, UnsupportedEncodingException,
            MalformedURLException, IOException
    {
        HttpURLConnection con = (HttpURLConnection) this.getRequest().openConnection();
        InputStream in = con.getInputStream();
        int i;
        String response = "";
        while ((i = in.read()) != -1) {
            response += (char) i;
        }
        return new BenItem(response);

    }
}
