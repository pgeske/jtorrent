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

    public void connect(Peer p) throws IOException, InvalidBencodeException {

        // Construct handshake ByteBuffer
        ByteBuffer handshakeBuffer = ByteBuffer.allocate(HANDSHAKE_LENGTH);
        handshakeBuffer.put((byte) PSTRLEN);
        handshakeBuffer.put(PSTR.getBytes("ISO-8859-1"));
        handshakeBuffer.put(new byte[8]);
        handshakeBuffer.put(this.torrent.infoHashBytes());
        handshakeBuffer.put(this.peerId.getBytes("ISO-8859-1"));
        handshakeBuffer.flip();

        // Setup handshake with peer
        SocketAddress address = new InetSocketAddress(p.getIp(), p.getPort());
        SocketChannel socketChannel = SocketChannel.open(address);
        socketChannel.write(handshakeBuffer);
        ByteBuffer response = ByteBuffer.allocate(HANDSHAKE_LENGTH);
        int bytesRead = socketChannel.read(response);
        response.flip();
        String responseString = "";
        System.out.println(response);
        while (response.hasRemaining()) {
            char c = (char) response.get();
            responseString += c;
        }
    }
}
