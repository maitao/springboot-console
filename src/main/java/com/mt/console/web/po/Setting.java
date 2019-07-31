package com.mt.console.web.po;

import java.io.Serializable;

public class Setting implements Serializable {

	private static final long serialVersionUID = -4273478262175216346L;
	private Long id;
	private Long accountId;
	private String account;
	private String layout;
	private String leftSidebarHover;
	private String leftSidebarCollapsed;
	private String rightSidebarCover;
	private String rightSidebarSkin;
	private int layoutSkin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getLeftSidebarHover() {
		return leftSidebarHover;
	}

	public void setLeftSidebarHover(String leftSidebarHover) {
		this.leftSidebarHover = leftSidebarHover;
	}

	public String getLeftSidebarCollapsed() {
		return leftSidebarCollapsed;
	}

	public void setLeftSidebarCollapsed(String leftSidebarCollapsed) {
		this.leftSidebarCollapsed = leftSidebarCollapsed;
	}

	public String getRightSidebarCover() {
		return rightSidebarCover;
	}

	public void setRightSidebarCover(String rightSidebarCover) {
		this.rightSidebarCover = rightSidebarCover;
	}

	public String getRightSidebarSkin() {
		return rightSidebarSkin;
	}

	public void setRightSidebarSkin(String rightSidebarSkin) {
		this.rightSidebarSkin = rightSidebarSkin;
	}

	public int getLayoutSkin() {
		return layoutSkin;
	}

	public void setLayoutSkin(int layoutSkin) {
		this.layoutSkin = layoutSkin;
	}

}
