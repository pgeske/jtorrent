package com.jtorrent.client.tracker;

import com.jtorrent.bencode.BenDecoder;
import com.jtorrent.bencode.BenItem;
import com.jtorrent.bencode.InvalidBencodeException;
import com.jtorrent.client.torrent.Torrent;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Philip on 12/10/2016.
 */
public class TrackerClient {
    private Torrent torrent;
    private String peerId;
    private TrackerRequest request;

    public TrackerClient(String peerId, Torrent torrent) {
        this.peerId = peerId;
        this.torrent = torrent;
        this.request = new TrackerRequest();
        this.request.setDefaults();
    }

    public TrackerRequest getRequest() {
        return this.request;
    }

    /**
     * Constructs a URL (a GET request) to send to the tracker. This URL utilizes all
     * parameters defined in trackerRequest, as well as the non-modifiable parameters in
     * metaInfo, namely 'info' and 'announce'.
     * @return The URL to send to the tracker.
     */
    public URL getRequestURL() throws InvalidBencodeException, UnsupportedEncodingException, MalformedURLException {
        String request = "";
        ArrayList<String> parameters = new ArrayList<String>();
        // Add SHA-1 info hash to request
        String infoHash = this.torrent.infoHash();
        String urlHash = "";
        for (int i = 0; i < infoHash.length(); i++) {
            if (i % 2 == 0) {
                urlHash += '%';
            }
            urlHash += infoHash.charAt(i);
        }
        parameters.add("info_hash=" + urlHash);
        // Append peer id
        if (this.peerId != null) {
            parameters.add("peer_id=" + this.peerId);
        }
        if (this.request.getPort() != null) {
            parameters.add("port=" + this.request.getPort());
        }
        if (this.request.getUploaded() != null) {
            parameters.add("uploaded=" + this.request.getUploaded());
        }
        if (this.request.getDownloaded() != null) {
            parameters.add("downloaded=" + this.request.getDownloaded());
        }
        if (this.request.getLeft() != null) {
            parameters.add("left=" + this.request.getLeft());
        }
        if (this.request.getCompact() != null) {
            parameters.add("compact=" + this.request.getCompact());
        }
        if (this.request.getEvent() != null) {
            parameters.add("event=" + this.request.getEvent());
        }
        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) {
                request += '&';
            }
            request += parameters.get(i);
        }
        //URL encode the get request
        return new URL(this.torrent.announce() + "?" + request);
    }

    /**
     * Creates an HTTP connection, sends the tracker requets, and returns the response
     * @return The tracker response as a BenItem.
     */
    public BenItem getResponse() throws IOException, InvalidBencodeException {
        URL request = this.getRequestURL();
        InputStream is = request.openStream();
        BufferedInputStream bs = new BufferedInputStream(is);
        return BenDecoder.decode(bs);
    }

    public void setRequest(TrackerRequest request) {
        this.request = request;
    }

}
