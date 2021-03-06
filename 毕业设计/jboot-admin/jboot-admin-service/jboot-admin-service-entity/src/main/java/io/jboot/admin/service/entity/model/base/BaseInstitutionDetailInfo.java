package io.jboot.admin.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseInstitutionDetailInfo<M extends BaseInstitutionDetailInfo<M>> extends JbootModel<M> implements IBean {

    public static final String ACTION_ADD = "InstitutionDetailInfo:add";
    public static final String ACTION_DELETE = "InstitutionDetailInfo:delete";
    public static final String ACTION_UPDATE = "InstitutionDetailInfo:update";


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


	public void setWdId(java.lang.String wdId) {
		set("wd_id", wdId);
	}
	
	public java.lang.String getWdId() {
		return getStr("wd_id");
	}

	public void setWdName(java.lang.String wdName) {
		set("wd_name", wdName);
	}
	
	public java.lang.String getWdName() {
		return getStr("wd_name");
	}

	public void setWdLead(java.lang.String wdLead) {
		set("wd_lead", wdLead);
	}
	
	public java.lang.String getWdLead() {
		return getStr("wd_lead");
	}

	public void setWdTel(java.lang.String wdTel) {
		set("wd_tel", wdTel);
	}
	
	public java.lang.String getWdTel() {
		return getStr("wd_tel");
	}

	public void setWdAddress(java.lang.String wdAddress) {
		set("wd_address", wdAddress);
	}
	
	public java.lang.String getWdAddress() {
		return getStr("wd_address");
	}

	public void setWdJd(java.lang.String wdJd) {
		set("wd_jd", wdJd);
	}
	
	public java.lang.String getWdJd() {
		return getStr("wd_jd");
	}

	public void setWdWd(java.lang.String wdWd) {
		set("wd_wd", wdWd);
	}
	
	public java.lang.String getWdWd() {
		return getStr("wd_wd");
	}

	public void setInstitutionId(java.lang.String institutionId) {
		set("institution_id", institutionId);
	}
	
	public java.lang.String getInstitutionId() {
		return getStr("institution_id");
	}

	public void setRepairNum(java.lang.Integer repairNum) {
		set("repair_num", repairNum);
	}
	
	public java.lang.Integer getRepairNum() {
		return getInt("repair_num");
	}

	public void setAddDate(java.util.Date addDate) {
		set("add_date", addDate);
	}
	
	public java.util.Date getAddDate() {
		return get("add_date");
	}

	public void setRepairDate(java.util.Date repairDate) {
		set("repair_date", repairDate);
	}
	
	public java.util.Date getRepairDate() {
		return get("repair_date");
	}

}
