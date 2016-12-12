package com.jtorrent.client.tracker;

import com.jtorrent.bencode.BenItem;
import com.jtorrent.bencode.InvalidBencodeException;

/**
 * Created by Philip on 12/10/2016.
 * This class acts as a container for all modifiable tracker request
 * parameters.
 */
public class TrackerRequest {

    // Request parameters
    private Integer port;
    private Integer uploaded;
    private Integer downloaded;
    private Integer left;
    private Integer compact;
    private String event;
    private String announce;

    public TrackerRequest() {
        this.port = 6888;
        this.uploaded = 0;
        this.downloaded = 0;
        this.left = 0;
        this.compact = 0;
        this.event = "started";
    }

    /**
     * Set this request's parameters to appropraite defaults.
     */
    public void setDefaults() {
        this.port = 6888;
        this.uploaded = 0;
        this.downloaded = 0;
        this.left = 0;
        this.compact = 0;
        this.event = "started";
    }

    /**
     * Sets all this's request parameters to null.
     */
    public void flush() {
        this.port = 0;
        this.uploaded = 0;
        this.downloaded = 0;
        this.left = 0;
        this.compact = 0;
        this.event = null;
    }

    /*
    Getters
     */
    public Integer getPort() { return this.port; }

    public Integer getUploaded() { return this.uploaded; }

    public Integer getDownloaded() { return this.downloaded; }

    public Integer getLeft() { return this.left; }

    public Integer getCompact() { return this.compact; }

    public String getEvent() { return this.event; }

    /*
    Setters
     */
    public void setPort(Integer port) { this.port = port; }

    public void setUploaded(Integer uploaded) { this.uploaded = uploaded; }

    public void setDownloaded(Integer downloaded) { this.downloaded = downloaded; }

    public void setLeft(Integer left) { this.left = left; }

    public void setCompact(Integer compact) { this.compact = compact; }

    public void setEvent(String event) { this.event = event; }

}
