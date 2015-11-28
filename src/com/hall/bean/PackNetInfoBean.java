package com.hall.bean;

public class PackNetInfoBean {
	private String gridname;

	private String name;
	private String monthcost;
	private String netflow;
	private String minute;
	private String ussdcode;
	private String costtype;
	private String packdes;

	// public PackNetInfoBean(String gridname, String name, String monthcost,
	// String netflow, String minute, String ussdcode) {
	// this.gridname = gridname;
	// this.name = name;
	// this.monthcost = monthcost;
	// this.netflow = netflow;
	// this.minute = minute;
	// this.ussdcode = ussdcode;
	// }

	public String getGridname() {
		return gridname;
	}

	public void setGridname(String gridname) {
		this.gridname = gridname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonthcost() {
		return monthcost;
	}

	public void setMonthcost(String monthcost) {
		this.monthcost = monthcost;
	}

	public String getNetflow() {
		return netflow;
	}

	public void setNetflow(String netflow) {
		this.netflow = netflow;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getUssdcode() {
		return ussdcode;
	}

	public void setUssdcode(String ussdcode) {
		this.ussdcode = ussdcode;
	}

	public String getCosttype() {
		// return "1：当月开通，次月1号理解扣费生效。\n2：中意通21 29 39三种套餐扣费方式是当月20号开通，次月20号扣费";
		return costtype;
	}

	public void setCosttype(String costtype) {
		this.costtype = costtype;
	}

	public String getPackdes() {
		// return "1：当月开通，次月1号理解扣费生效。\n2：中意通21 29 39三种套餐扣费方式是当月20号开通，次月20号扣费";
		return packdes;
	}

	public void setPackdes(String packdes) {
		this.packdes = packdes;
	}
}
