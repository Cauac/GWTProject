package gproject.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.RangeChangeEvent;
import gproject.client.AppContext;
import gproject.client.event.ShowCommentRange;
import gproject.shared.Comment;

import java.util.Arrays;

public class CommentView extends Composite implements Display {

    @UiTemplate("template/CommentView.ui.xml")
    interface CommentViewUiBinder extends UiBinder<Widget, CommentView> {
    }

    private static CommentViewUiBinder uiBinder = GWT.create(CommentViewUiBinder.class);

    static private CommentView instance = null;


    public static CommentView getInstance() {
        if (null == instance) {
            instance = new CommentView();
        }
        return instance;
    }

    @UiField
    CellTable<Comment> cellTable;
    @UiField
    SimplePager pager;

    public CommentView() {
        initWidget(uiBinder.createAndBindUi(this));
        cellTable.setWidth("100%", true);
        pager.setDisplay(cellTable);
        initColumns();
        cellTable.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {
                AppContext.getInstance().getEventBus().fireEvent(new ShowCommentRange(event.getNewRange()));
            }
        });
    }

    private void initColumns() {
        cellTable.addColumn(new TextColumn<Comment>() {
            @Override
            public String getValue(Comment object) {
                return object.getItemType();
            }
        },"Item Type");
        cellTable.addColumn(new TextColumn<Comment>() {
            @Override
            public String getValue(Comment object) {
                return object.getItemTitle();
            }
        }, "Title");
        cellTable.addColumn(new TextColumn<Comment>() {
            @Override
            public String getValue(Comment object) {
                return object.getUsername();
            }
        }, "User Name");
        cellTable.addColumn(new TextColumn<Comment>() {
            @Override
            public String getValue(Comment object) {
                return object.getComment();
            }
        }, "Comment");
        cellTable.addColumn(new TextColumn<Comment>() {
            @Override
            public String getValue(Comment object) {
                return DateTimeFormat.getFormat("dd-MM-yyyy").format(object.getDate());
            }
        }, "Date");
    }

    public void setCommentCount(int count) {
        cellTable.setRowCount(count);
        AppContext.getInstance().getEventBus().fireEvent(new ShowCommentRange(cellTable.getVisibleRange()));
    }

    public void setCommentRange(int start, Comment[] comments) {
        cellTable.setRowData(start, Arrays.asList(comments));
    }
}
