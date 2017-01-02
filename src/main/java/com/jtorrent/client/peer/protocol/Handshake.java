package com.jtorrent.client.peer.protocol;

import com.jtorrent.bencode.InvalidBencodeException;
import com.jtorrent.client.torrent.Torrent;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Created by Philip on 1/1/2017.
 */
public class Handshake {
    public static final int HANDSHAKE_LENGTH = 68;
    public static final String PSTR = "BitTorrent protocol";

    private String pstr = this.PSTR;
    private byte[] reserved = new byte[8];
    private byte[] infoHash;
    private String peerId;


    public Handshake(byte[] infoHash, String peerId) {
        this.infoHash = infoHash;
        this.peerId = peerId;
    }

    public Handshake(Torrent torrent, String peerId) throws UnsupportedEncodingException, InvalidBencodeException {
        this(torrent.infoHashBytes(), peerId);
    }

    /**
     * Unpacks a ByteBuffer into a Handshake.
     * @param byteBuffer The ByteBuffer (in READ mode) to be unpacked
     */
    public Handshake(ByteBuffer byteBuffer) {
        int pstrLen = byteBuffer.get() & 0xFF;
        byte[] pstr = new byte[pstrLen];
        byteBuffer.get(pstr);
        this.pstr = new String(pstr);
        byteBuffer.get(this.reserved);
        byte[] infoHash = new byte[20];
        byteBuffer.get(infoHash);
        byte[] peerId = new byte[20];
        byteBuffer.get(peerId);
        this.peerId = new String(peerId);
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

    /**
     * Packs Handshake parameters into a ByteBuffer, which can be fed into a peer connection.
     * @return The handshake ByteBuffer in WRITE mode.
     * @throws UnsupportedEncodingException
     * @throws InvalidBencodeException
     */
    public ByteBuffer getBuffer() throws UnsupportedEncodingException, InvalidBencodeException {
        ByteBuffer handshakeBuffer = ByteBuffer.allocate(HANDSHAKE_LENGTH);
        handshakeBuffer.put((byte) this.pstr.length());
        handshakeBuffer.put(this.pstr.getBytes("ISO-8859-1"));
        handshakeBuffer.put(new byte[8]);
        handshakeBuffer.put(this.infoHash);
        handshakeBuffer.put(this.peerId.getBytes("ISO-8859-1"));
        return handshakeBuffer;
    }

}
