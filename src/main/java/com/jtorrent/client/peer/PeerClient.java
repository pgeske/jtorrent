package com.jtorrent.client.peer;

import com.jtorrent.bencode.InvalidBencodeException;
import com.jtorrent.client.torrent.Torrent;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Philip on 12/10/2016.
 */
public class PeerClient {
    private static final String PSTR = "BitTorrent protocol";
    private static final int PSTRLEN = 19;
    private static final int HANDSHAKE_LENGTH = 68;

    private String peerId;
    private Torrent torrent;
    private Socket[] connections;

    public PeerClient (String peerId, Torrent torrent) {
        this.peerId = peerId;
        this.torrent = torrent;
    }
}
