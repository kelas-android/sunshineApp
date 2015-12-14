package net.ruangtedy.java.android.sunshine.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by tedy.saputro on 14/12/2015.
 */
public class Snow {
    @JsonProperty("3h")
    private String _3h;

    public String get_3h() {
        return _3h;
    }

    public void set_3h(String _3h) {
        this._3h = _3h;
    }
}
