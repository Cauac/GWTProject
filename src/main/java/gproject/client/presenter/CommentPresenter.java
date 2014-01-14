package gproject.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.Range;
import gproject.client.AppContext;
import gproject.client.event.ShowCommentRange;
import gproject.client.event.ShowCommentRangeHandler;
import gproject.client.service.FlickrService;
import gproject.client.service.FlickrServiceAsync;
import gproject.client.view.CommentView;
import gproject.shared.Comment;

public class CommentPresenter implements Presenter {

    private FlickrServiceAsync flickrService = GWT.create(FlickrService.class);
    private final CommentView display=CommentView.getInstance();

    private void bind() {
        AppContext.getInstance().getEventBus().addHandler(ShowCommentRange.TYPE, new ShowCommentRangeHandler() {
            @Override
            public void show(ShowCommentRange event) {
                fetchComments(event.getRange());
            }
        });
    }

    @Override
    public void go(HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
        fetchCommentCount();
    }

    private void fetchCommentCount() {
        flickrService.getCommentCount(new AsyncCallback<Integer>() {
            @Override
            public void onFailure(Throwable caught) {
                return;
            }

            @Override
            public void onSuccess(Integer result) {
                display.setCommentCount(result);
            }
        });
    }

    private void fetchComments(Range range) {
        final Range r = range;
        flickrService.getComments(range.getStart(), range.getLength(), new AsyncCallback<Comment[]>() {
            @Override
            public void onFailure(Throwable caught) {
                return;
            }

            @Override
            public void onSuccess(Comment[] result) {
                display.setCommentRange(r.getStart(), result);
            }
        });
    }
}
