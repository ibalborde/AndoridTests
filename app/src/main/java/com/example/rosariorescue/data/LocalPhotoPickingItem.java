package com.example.rosariorescue.data;

import android.net.Uri;

public class LocalPhotoPickingItem extends PhotoPickingItem {

    // MARK: - Init

    public LocalPhotoPickingItem(Uri uri) {
        this.uri = uri;
    }

}
