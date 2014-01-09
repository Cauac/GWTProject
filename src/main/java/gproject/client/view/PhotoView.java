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
import com.google.gwt.view.client.ListDataProvider;
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
    }

    public void showPhoto(Photo[] photos) {
        cellTable.setWidth("100%", true);
        pager.setDisplay(cellTable);
        ListDataProvider<Photo> dataProvider = new ListDataProvider<Photo>();
        dataProvider.setList(Arrays.asList(photos));
        dataProvider.addDataDisplay(cellTable);
        initColumns();
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
                    Image image = new Image(photo.getLargeSizeUrl());
                    DialogView.getInstance().show("Photo", image);
                }
            }
        });
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
