package com.cn.tonyou.pojo;

import java.util.Date;

/**
 * MessageInfo entity. @author 推送信息表实体类
 */

public class MessageInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messageId;
	private String messageName;
	private Date messageCate;
	private String messagContent;
	private SysUserInfo sysUser;//发送消息的人
	private String messageImage;
	private String allowClassIds;//接受消息的班级
	private String allowRoles;//接受消息的角色
	private String accessorUrl;//附件内容

	// Constructors

	/** default constructor */
	public MessageInfo() {
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public Date getMessageCate() {
		return messageCate;
	}

	public void setMessageCate(Date messageCate) {
		this.messageCate = messageCate;
	}

	public String getMessagContent() {
		return messagContent;
	}

	public void setMessagContent(String messagContent) {
		this.messagContent = messagContent;
	}

	public SysUserInfo getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserInfo sysUser) {
		this.sysUser = sysUser;
	}

	public String getMessageImage() {
		return messageImage;
	}

	public void setMessageImage(String messageImage) {
		this.messageImage = messageImage;
	}

	public String getAllowClassIds() {
		return allowClassIds;
	}

	public void setAllowClassIds(String allowClassIds) {
		this.allowClassIds = allowClassIds;
	}

	

	public String getAllowRoles() {
		return allowRoles;
	}

	public void setAllowRoles(String allowRoles) {
		this.allowRoles = allowRoles;
	}

	public String getAccessorUrl() {
		return accessorUrl;
	}

	public void setAccessorUrl(String accessorUrl) {
		this.accessorUrl = accessorUrl;
	}

	

}