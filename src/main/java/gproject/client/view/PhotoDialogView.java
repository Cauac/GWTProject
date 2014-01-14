package gproject.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import gproject.shared.Photo;
import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Series;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhotoDialogView {

    private static PhotoDialogView instance;
    private DialogBox dialogBox;
    private VerticalPanel content;

    public static PhotoDialogView getInstance() {
        if (null == instance) {
            instance = new PhotoDialogView();
        }
        return instance;
    }

    public PhotoDialogView() {
        dialogBox = new DialogBox();
        content = new VerticalPanel();
        VerticalPanel dialogPanel = new VerticalPanel();
        dialogPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        dialogPanel.setSpacing(4);
        dialogPanel.add(content);
        Button button = new Button("Close", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });
        button.setStyleName("btn btn-primary");
        dialogPanel.add(button);
        dialogBox.setWidget(dialogPanel);
    }

    public void showEmptyDialog(Photo photo){
        dialogBox.setText(photo.getTitle());
        content.clear();
        content.add(new Label("Comments not found"));
        dialogBox.center();
        dialogBox.show();
    }

    public void showPhoto(Photo photo, String jsonData) {
        JSONObject data = JSONParser.parseStrict(jsonData).isObject();
        JSONArray comments = data.get("comments").isArray();
        JSONArray dates = data.get("dates").isArray();
        JSONArray values = data.get("values").isArray();
        dialogBox.setText(photo.getTitle());
        content.clear();
        Chart chart = new Chart().setType(Series.Type.AREA).setChartTitleText("Nice Chart").setMarginRight(10);
        Number[] numbers = new Number[values.size()];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(values.get(i).toString());
        }
        String[] categories = new String[dates.size()];
        for (int i = 0; i < categories.length; i++) {
            categories[i] = dates.get(i).toString();
        }
        Series series = chart.createSeries().setPoints(numbers);
        chart.getXAxis().setCategories(categories);
        chart.addSeries(series);
        CellTable table = createTable();
        List<JSONValue> list = new ArrayList(comments.size());
        for (int i = 0; i < comments.size(); i++) {
            list.add(comments.get(i));
        }
        table.setRowData(list);
        content.add(chart);
        content.add(table);
        content.setWidth("700px");
        dialogBox.center();
        dialogBox.show();
    }

    private CellTable createTable() {
        CellTable<JSONValue> table = new CellTable();
        table.setWidth("100%",true);
        table.addColumn(new TextColumn<JSONValue>() {
            @Override
            public String getValue(JSONValue object) {
                return object.isObject().get("username").toString();
            }
        }, "UserName");
        table.addColumn(new TextColumn<JSONValue>() {
            @Override
            public String getValue(JSONValue object) {
                return object.isObject().get("_content").toString();
            }
        }, "Comment");
        table.addColumn(new TextColumn<JSONValue>() {
            @Override
            public String getValue(JSONValue object) {
                String dateadded = object.isObject().get("dateadded").isString().stringValue() + "000";
                Date date = new Date(Long.parseLong(dateadded));
                return DateTimeFormat.getFormat("dd-MM-yyyy").format(date);
            }
        }, "Date");
        return table;
    }
}
