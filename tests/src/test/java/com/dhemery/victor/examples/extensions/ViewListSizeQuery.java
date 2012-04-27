package com.dhemery.victor.examples.extensions;

import com.dhemery.polling.Query;
import com.dhemery.victor.IosView;

public class ViewListSizeQuery extends Query<IosView, Integer> {
    @Override
    public String name() {
        return "size";
    }

    @Override
    public Integer query(IosView view) {
        return view.sendMessage("tag").size();
    }

    public static Query<IosView, Integer> size() {
        return new ViewListSizeQuery();
    }
}
