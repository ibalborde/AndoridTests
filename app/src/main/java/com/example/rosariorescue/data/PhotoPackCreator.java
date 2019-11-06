package com.example.rosariorescue.data;

import com.example.rosariorescue.event.PhotoPackPickedImageDeleteEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class PhotoPackCreator {

    // MARK: - Instance

    private static PhotoPackCreator instance;

    public static PhotoPackCreator get() {
        if (instance == null) {
            instance = new PhotoPackCreator();
        }
        return instance;
    }

    private int selectedCount = 0;

    private ArrayList<PhotoPickingItem> items = new ArrayList<>();

    // MARK: - Init

    private PhotoPackCreator() {}

    // MARK: - Get & Set

    // MARK Interface

    /**
     * Cantidad de fotos (sin repetición)
     */
    public int getDiffPhotosCount() {
        return items.size();
    }

    /**
     * Cantidad total de fotos (con repetición)
     */
    public int getSelectedCount() {
        return selectedCount;
    }

    /**
     * Consulta la cantidad de fotografías que permite el paquete
     */


    /**
     * Limpia los datos
     */
    public void clear() {
        this.selectedCount = 0;
        this.items = new ArrayList<>();
    }

    /**
     * Consulta si se puede agregar una cantidad de fotografías
     */
    public boolean canAdd(int count) {
        int newCount = selectedCount + count;

        if (newCount < 0) {
            return false;
        }

        return newCount <= 10;
    }

    public boolean addImage(PhotoPickingItem photoPickingItem) {
        if (!canAdd(1)) {
            return false;
        }

        this.applyDelta(1);
        this.items.add(photoPickingItem);

        return true;
    }

    public void removeImage(PhotoPickingItem photoPickingItem) {
        int index = this.items.indexOf(photoPickingItem);
        if (index == -1) { return; }

        this.items.remove(index);
        this.applyDelta(-photoPickingItem.getCount());
        EventBus.getDefault().post(new PhotoPackPickedImageDeleteEvent(index));
    }

    public PhotoPickingItem getPickedItem(int index) {
        return this.items.get(index);
    }

    public List<PhotoPickingItem> getDiffPhotos() {
        return this.items;
    }

    public void applyDelta(int delta) {
        this.selectedCount += delta;
        EventBus.getDefault().post(new PhotoPackCounterUpdateEvent());
    }

}
