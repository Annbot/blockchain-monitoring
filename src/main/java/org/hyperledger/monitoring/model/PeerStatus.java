package org.hyperledger.monitoring.model;

public enum PeerStatus {
    DOWN,
    UP;

    public PeerStatus getOtherStatus() {
        if (this == DOWN) {
            return UP;
        } else {
            return DOWN;
        }
    }
}
