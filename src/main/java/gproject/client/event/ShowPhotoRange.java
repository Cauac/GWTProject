package gproject.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.view.client.Range;

public class ShowPhotoRange extends GwtEvent<ShowPhotoRangeHandler> {

    private Range range;

    public ShowPhotoRange(Range range) {
        this.range = range;
    }

    public static Type<ShowPhotoRangeHandler> TYPE = new Type<ShowPhotoRangeHandler>();

    @Override
    public Type<ShowPhotoRangeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShowPhotoRangeHandler handler) {
        handler.show(this);
    }

    public Range getRange() {
        return range;
    }
}
