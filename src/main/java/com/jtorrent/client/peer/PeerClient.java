package com.jtorrent.client.peer;

import com.jtorrent.bencode.InvalidBencodeException;
import com.jtorrent.client.torrent.Torrent;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

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
        // Construct the handshake byte[]
        ByteBuffer handshakeBuffer = ByteBuffer.allocate(HANDSHAKE_LENGTH);
        byte[] reserved = new byte[8];
        handshakeBuffer.put((byte) 19);
        handshakeBuffer.put(PSTR.getBytes("ISO-8859-1"));
        handshakeBuffer.put(new byte[8]);
        handshakeBuffer.put(this.torrent.infoHashBytes());
        handshakeBuffer.put(this.peerId.getBytes("ISO-8859-1"));

        // Setup a handshake with the peer
        Socket s = new Socket(p.getIp(), p.getPort());
        System.out.println(s);
        BufferedInputStream in = new BufferedInputStream(s.getInputStream());
        OutputStream out = s.getOutputStream();
        out.write(handshakeBuffer.array());
        String response = "";
        for (int i = 0; i < HANDSHAKE_LENGTH; i++) {
            int r = in.read();
            response += (char) r;
        }
        System.out.println(response);

    }
}
