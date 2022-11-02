package com.outpost.box;

public enum Status {

    PREPARED,
    PLACED_IN_SENDER_PARCEL_LOCKER,
    JOURNEY_TO_CENTRAL,
    CENTRAL,
    JOURNEY_TO_RECIPIENT_PARCEL_LOCKER,
    PLACED_IN_RECIPIENT_PARCEL_LOCKER,
    DELIVERED;

    public Status getNext() {
        Status[] e = Status.values();
        int i = 0;
        for (; e[i] != this; i++);
        i++;
        i %= e.length;
        return e[i];
    }
}
