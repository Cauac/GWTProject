package gproject.client.view;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class ImageCell extends AbstractCell<String> {

    private int width;
    private int height;

    interface Template extends SafeHtmlTemplates {
        @Template("<img src=\"{0}\" width=\"{1}\" height=\"{2}\"/>")
        SafeHtml img(String url, int width, int height);
    }

    private static Template template;

    /**
     * Construct a new ImageCell.
     */
    public ImageCell(int width, int height) {
        this.width = width;
        this.height = height;
        if (template == null) {
            template = GWT.create(Template.class);
        }
    }

    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        if (value != null) {
            // The template will sanitize the URI.
            sb.append(template.img(value, width, height));
        }
    }
}
