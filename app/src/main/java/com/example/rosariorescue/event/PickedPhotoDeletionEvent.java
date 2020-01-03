package com.example.rosariorescue.event;

import android.net.Uri;

public class PickedPhotoDeletionEvent {

    public Uri uri;

    public PickedPhotoDeletionEvent(Uri uri) {
        this.uri = uri;
    }

}
