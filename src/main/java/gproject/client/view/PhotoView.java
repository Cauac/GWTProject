package gproject.client.view;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.RangeChangeEvent;
import gproject.client.AppContext;
import gproject.client.event.ShowPhotoDialog;
import gproject.client.event.ShowPhotoRange;
import gproject.shared.Photo;

import java.util.Arrays;

public class PhotoView extends Composite implements Display {

    @UiTemplate("template/PhotoView.ui.xml")
    interface PhotoViewUiBinder extends UiBinder<Widget, PhotoView> {
    }

    private static PhotoViewUiBinder uiBinder = GWT.create(PhotoViewUiBinder.class);

    static private PhotoView instance = null;


    public static PhotoView getInstance() {
        if (null == instance) {
            instance = new PhotoView();
        }
        return instance;
    }

    @UiField
    CellTable<Photo> cellTable;
    @UiField
    SimplePager pager;

    public PhotoView() {
        initWidget(uiBinder.createAndBindUi(this));
        cellTable.setWidth("100%", true);
        pager.setDisplay(cellTable);
        initColumns();
        cellTable.addRangeChangeHandler(new RangeChangeEvent.Handler() {
            @Override
            public void onRangeChange(RangeChangeEvent event) {
                AppContext.getInstance().getEventBus().fireEvent(new ShowPhotoRange(event.getNewRange()));
            }
        });
    }

    public void setPhotoCount(int photoCount) {
        cellTable.setRowCount(photoCount);
        AppContext.getInstance().getEventBus().fireEvent(new ShowPhotoRange(cellTable.getVisibleRange()));
    }

    public void setPhotoRange(int start, Photo[] photos) {
        cellTable.setRowData(start, Arrays.asList(photos));
    }

    public void initColumns() {
        cellTable.addColumn(new TextColumn<Photo>() {
            @Override
            public String getValue(Photo object) {
                return object.getTitle();
            }
        }, "Title");
        cellTable.addColumn(new TextColumn<Photo>() {
            @Override
            public String getValue(Photo object) {
                return object.getOwnerName();
            }
        }, "Owner name");
        cellTable.addColumn(new TextColumn<Photo>() {
            @Override
            public String getValue(Photo object) {
                return object.getViews();
            }
        }, "Views");
        cellTable.addColumn(new TextColumn<Photo>() {
            @Override
            public String getValue(Photo object) {
                return DateTimeFormat.getFormat("dd-MM-yyyy").format(object.getDateupload());
            }
        }, "Date Upload");
        cellTable.addColumn(new TextColumn<Photo>() {
            @Override
            public String getValue(Photo object) {
                return DateTimeFormat.getFormat("dd-MM-yyyy").format(object.getLastupdate());
            }
        }, "Last Update");
        cellTable.addColumn(new Column<Photo, String>(new ImageCell()) {
            @Override
            public String getValue(Photo object) {
                return object.getUrl();
            }

            @Override
            public void onBrowserEvent(Cell.Context context, Element elem, Photo object, NativeEvent event) {

                Window.alert("1");
            }
        }, "photo");

        cellTable.addCellPreviewHandler(new CellPreviewEvent.Handler<Photo>() {
            @Override
            public void onCellPreview(CellPreviewEvent<Photo> event) {
                if (event.getNativeEvent().getType().equals("mousedown")) {
                    Photo photo = event.getValue();
                    AppContext.getInstance().getEventBus().fireEvent(new ShowPhotoDialog(photo));
                }
            }
        });
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
