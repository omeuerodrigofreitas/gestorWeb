/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItems;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author rodrigo
 */
@ManagedBean
//@Named(value = "autoComplete")
@SessionScoped
public class AutoComplete implements Serializable {

    private static String COMPLETION_ITEMS_ATTR = "corejsf.competionsItems";

    public void valueChanged(ValueChangeEvent e) {

        UIInput input = (UIInput) e.getSource();

        UISelectOne listbox = (UISelectOne) input.findComponent("listbox");

//        if (listbox != null) {
//
//            UISelectItems items = (UISelectItems) listbox.getChildren().get(0);
//            Using Ajax in Composite Components Map<String, Object> attrs = listbox.getAttributes();
//            List<String> newItems = getNewItems((String) input.getValue(),
//                    getCompletionItems(listbox, items, attrs));
//            items.setValue(newItems.toArray());
//            setListboxStyle(newItems.size(), attrs);
//        }
    }

    private List<String> getNewItems(String inputValue, String[] completionItems) {
        List<String> newItems = new ArrayList<String>();
        for (String item : completionItems) {
            String s = item.substring(0, inputValue.length());
            if (s.equalsIgnoreCase(inputValue)) {
                newItems.add(item);
            }
        }
        return newItems;
    }

    private void setListboxStyle(int rows, Map<String, Object> attrs) {
        if (rows > 0) {
            Map<String, String> reqParams = FacesContext.getCurrentInstance()
                    .getExternalContext().getRequestParameterMap();
            attrs.put("style", "display: inline; position: absolute; left: "
                    + reqParams.get("x") + "px;" + " top: " + reqParams.get("y") + "px");
        } else {
            attrs.put("style", "display: none;");
        }
    }

    private String[] getCompletionItems(UISelectOne listbox,
            UISelectItems items, Map<String, Object> attrs) {
        String[] completionItems = (String[]) attrs.get(COMPLETION_ITEMS_ATTR);
        if (completionItems == null) {
            completionItems = (String[]) items.getValue();
            attrs.put(COMPLETION_ITEMS_ATTR, completionItems);
        }
        return completionItems;
    }

    public void completionItemSelected(ValueChangeEvent e) {
        UISelectOne listbox = (UISelectOne) e.getSource();
        UIInput input = (UIInput) listbox.findComponent("input");

        if (input != null) {
            input.setValue(listbox.getValue());
        }
        Map<String, Object> attrs = listbox.getAttributes();

        attrs.put(
                "style", "display: none");
    }
}
