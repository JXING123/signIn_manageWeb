package com.cn.tonyou.pojo;
public class TreeInfoModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 利用树必须要定义这几个属性
	 */
	private String id;
	//父权限id
	private String pId;
	//显示的名称
	private String name;
	private boolean checked;				//是否勾选
	private boolean open = false;			//是否展开
	private boolean chkDisabled = false;	//是否不可选择
	private boolean isHidden = false;		//是否隐藏
	private String iconSkin;				//采用css样式的方式
	//采用直接放图片路径方式
	private String iconOpen = "css/zTreeStyle/img/diy/1_open.png";
	private String iconClose = "css/zTreeStyle/img/diy/1_close.png";
	private String icon = "css/zTreeStyle/img/diy/9.png";
	public TreeInfoModel() {
	}
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}