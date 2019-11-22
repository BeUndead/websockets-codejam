package com.com.websockets.api;

import com.google.gson.annotations.Expose;

public class SocketMessage {

    private @Expose String message;
    private @Expose String to;

    public SocketMessage(final String from, final String to) {
        this.message = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("{from: %s, to: %s}", this.message, this.to);
    }

    public String getMessage() {
        return this.message;
    }

    public String getTo() {
        return this.to;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setTo(final String to) {
        this.to = to;
    }
}
