package com.hall.bean;

public class HallUserInfo {

	private String phone;
	private String passwd;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	private static HallUserInfo u_info;

	private HallUserInfo() {

	}

	public static HallUserInfo getInstance() {
		if (u_info == null) {
			u_info = new HallUserInfo();
		}
		return u_info;
	}

	public static boolean isPhone() {
		HallUserInfo info = getInstance();
//		if (info.getPhone() != null && !info.getPhone().equals("")) {
			return true;
//		} else {
//			return false;
//		}

	}
}
