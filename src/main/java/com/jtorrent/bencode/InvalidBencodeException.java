package com.jtorrent.bencode;

/**
 * Created by pgeske on 10/8/16.
 */
public class InvalidBencodeException extends Exception {
    public InvalidBencodeException(String message) {
        super(message);
    }
}
