package se.umu.carl.thirty.Views;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SpinnerItems {

    /**
     * Hämtar hela listan på alla element i spinnern
     * @param theSpinner - spinnern
     * @return items
     */
    public ArrayList<String> retrieveAllItems(Spinner theSpinner) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) theSpinner.getAdapter();
        int count = adapter.getCount();
        ArrayList<String> items = new ArrayList<String>(count);
        for (int i = 0; i < count; i++) {
            String item = adapter.getItem(i);
            items.add(item);
        }
        return items;
    }
}
