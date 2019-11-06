package com.example.rosariorescue.data;

import android.graphics.Rect;
import android.net.Uri;

public class PhotoPickingItem {

    // MARK: - Data

    int count = 1;

    Uri uri;

    Rect croppedArea;

    // MARK: - get set

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Rect getCroppedArea() {
        return croppedArea;
    }

    public void setCroppedArea(Rect croppedArea) {
        this.croppedArea = croppedArea;
    }

}
