package com.mt.console.web.po.enumpo;


public enum LayoutSkin {

	BLUE(1, "skin-blue"), BLACK(2, "skin-black"), RED(3, "skin-red"), YELLOW(4, "skin-yellow"), PURPLE(5,
			"skin-purple"), GREEN(6, "skin-green"), BLUE_LIGHT(7, "skin-blue-light"), BLACK_LIGHT(8,
					"skin-black-light"), RED_LIGHT(9, "skin-red-light"), YELLOW_LIGHT(10,
							"skin-yellow-light"), PURPLE_LIGHT(11,
									"skin-purple-light"), GREEN_LIGHT(12, "skin-green-light");

	LayoutSkin(int i, String value) {
		this.i = i;
		this.value = value;
	}

	private int i;
	private String value;

}
