package com.jtorrent.client.peer;

/**
 * Created by Philip on 12/11/2016.
 */
public class Peer {
    private String id;
    private String ip;
    private Integer port;

    public Peer(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public String getId() { return this.id; }

    public String getIp() { return this.ip; }

    public Integer getPort() { return this.port; }

}
