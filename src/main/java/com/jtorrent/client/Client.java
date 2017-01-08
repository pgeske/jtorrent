package com.jtorrent.client;

import com.jtorrent.bencode.BenItem;
import com.jtorrent.bencode.InvalidBencodeException;
import com.jtorrent.client.peer.Peer;
import com.jtorrent.client.peer.PeerClient;
import com.jtorrent.client.torrent.Torrent;
import com.jtorrent.client.tracker.TrackerClient;

import java.io.IOException;
import java.net.ConnectException;

/**
 * Created by pgeske on 10/23/16.
 */
public class Client {
    private TrackerClient trackerClient;
    private PeerClient peerClient;
    private String peerId;

    public Client(Torrent t) {
        this.peerId = "akdlakdiejsldifjelsk";
        this.trackerClient = new TrackerClient(this.peerId, t);
        this.peerClient = new PeerClient(this.peerId, t);
    }

    /**
     * Returns array of peers from tracker client response
     * @return Array of peers from tracker
     */
    public Peer[] getPeers() throws IOException, InvalidBencodeException {
        BenItem response = this.trackerClient.getResponse();
        byte[] peerData = response.find("peers").getString().getBytes("ISO-8859-1");
        Peer[] peers = new Peer[peerData.length / 6];
        for (int i = 0; i < peers.length; i++) {
            int start = i * 6;
            // Add IP
            String ip = "";
            for (int j = start; j < start + 4; j++) {
                int chunk = peerData[j] & 0xFF;
                ip += chunk;
                if (j < start + 3) ip += ".";
            }
            // Add port
            int port = ((0xFF & peerData[start + 4]) << 8) | (0xFF & peerData[start + 5]);
            Peer peer = new Peer(ip, port);
            peers[i] = peer;
        }
        return peers;
    }
}
