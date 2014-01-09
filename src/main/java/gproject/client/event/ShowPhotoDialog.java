package gproject.client.event;

import com.google.gwt.event.shared.GwtEvent;
import gproject.shared.Photo;

public class ShowPhotoDialog extends GwtEvent<ShowPhotoDialogEventHandler> {
    private Photo photo;

    public ShowPhotoDialog(Photo photo) {
        this.photo = photo;
    }

    public static Type<ShowPhotoDialogEventHandler> TYPE = new Type<ShowPhotoDialogEventHandler>();

    @Override
    public Type<ShowPhotoDialogEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShowPhotoDialogEventHandler handler) {
        handler.onShow(this);
    }

    public Photo getPhoto() {
        return photo;
    }
}
