package gproject.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.view.client.Range;

public class ShowCommentRange extends GwtEvent<ShowCommentRangeHandler> {

    private Range range;

    public ShowCommentRange(Range range) {
        this.range = range;
    }

    public static Type<ShowCommentRangeHandler> TYPE = new Type<ShowCommentRangeHandler>();

    @Override
    public Type<ShowCommentRangeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShowCommentRangeHandler handler) {
        handler.show(this);
    }

    public Range getRange() {
        return range;
    }
}