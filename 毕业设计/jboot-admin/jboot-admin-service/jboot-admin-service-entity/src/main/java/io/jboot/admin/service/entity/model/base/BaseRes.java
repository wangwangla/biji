package io.jboot.admin.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRes<M extends BaseRes<M>> extends JbootModel<M> implements IBean {

    public static final String ACTION_ADD = "Res:add";
    public static final String ACTION_DELETE = "Res:delete";
    public static final String ACTION_UPDATE = "Res:update";


    @Override
    public String addAction() {
        return ACTION_ADD;
    }

    @Override
    public String deleteAction() {
        return ACTION_DELETE;
    }

    @Override
    public String updateAction() {
        return ACTION_UPDATE;
    }


	public void setId(java.lang.Long id) {
		set("id", id);
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public void setPid(java.lang.Long pid) {
		set("pid", pid);
	}
	
	public java.lang.Long getPid() {
		return getLong("pid");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setDes(java.lang.String des) {
		set("des", des);
	}
	
	public java.lang.String getDes() {
		return getStr("des");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

	public void setLevel(java.lang.Integer level) {
		set("level", level);
	}
	
	public java.lang.Integer getLevel() {
		return getInt("level");
	}

	public void setIconCls(java.lang.String iconCls) {
		set("iconCls", iconCls);
	}
	
	public java.lang.String getIconCls() {
		return getStr("iconCls");
	}

	public void setSeq(java.lang.Long seq) {
		set("seq", seq);
	}
	
	public java.lang.Long getSeq() {
		return getLong("seq");
	}

	public void setType(java.lang.String type) {
		set("type", type);
	}
	
	public java.lang.String getType() {
		return getStr("type");
	}

	public void setStatus(java.lang.String status) {
		set("status", status);
	}
	
	public java.lang.String getStatus() {
		return getStr("status");
	}

	public void setLastUpdAcct(java.lang.String lastUpdAcct) {
		set("lastUpdAcct", lastUpdAcct);
	}
	
	public java.lang.String getLastUpdAcct() {
		return getStr("lastUpdAcct");
	}

	public void setLastUpdTime(java.util.Date lastUpdTime) {
		set("lastUpdTime", lastUpdTime);
	}
	
	public java.util.Date getLastUpdTime() {
		return get("lastUpdTime");
	}

	public void setNote(java.lang.String note) {
		set("note", note);
	}
	
	public java.lang.String getNote() {
		return getStr("note");
	}

}
