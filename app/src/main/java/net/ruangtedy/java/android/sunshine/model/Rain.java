package net.ruangtedy.java.android.sunshine.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Rain {
	@JsonProperty("3h")
	private double _3h;

	public double get_3h() {
		return _3h;
	}

	public void set_3h(double _3h) {
		this._3h = _3h;
	}


	

}
