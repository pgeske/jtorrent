package com.jtorrent.client.peer.protocol;

import com.jtorrent.bencode.InvalidBencodeException;
import com.jtorrent.client.torrent.Torrent;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Created by Philip on 1/1/2017.
 */
public class Handshake {
    private static final int HANDSHAKE_LENGTH = 68;
    private static final String PSTR = "Bittorrent protocol";

    private String pstr;
    private byte[] reserved;
    private byte[] infoHash;
    private String peerId;


    public Handshake(byte[] infoHash, String peerId) {
        this.infoHash = infoHash;
        this.peerId = peerId;
        this.pstr = this.PSTR;
        this.reserved = new byte[8];
    }

    public Handshake(Torrent torrent, String peerId) throws UnsupportedEncodingException, InvalidBencodeException {
        this(torrent.infoHashBytes(), peerId);
    }

    public Handshake(ByteBuffer byteBuffer) {

    }

    public String getPstr() {
        return this.pstr;
    }

    public byte[] getReserved() {
        return this.reserved;
    }

    public byte[] getInfoHash() {
        return this.infoHash;
    }

    public String getPeerId() {
        return this.peerId;
    }

    public ByteBuffer getBuffer() throws UnsupportedEncodingException, InvalidBencodeException {
        ByteBuffer handshakeBuffer = ByteBuffer.allocate(this.HANDSHAKE_LENGTH);
        handshakeBuffer.put((byte) this.pstr.length());
        handshakeBuffer.put(this.pstr.getBytes("ISO-8859-1"));
        handshakeBuffer.put(new byte[8]);
        handshakeBuffer.put(this.infoHash);
        handshakeBuffer.put(this.peerId.getBytes("ISO-8859-1"));
        return handshakeBuffer;
    }

}
