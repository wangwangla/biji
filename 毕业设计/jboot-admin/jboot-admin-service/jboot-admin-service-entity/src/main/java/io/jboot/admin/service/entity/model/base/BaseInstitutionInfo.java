package io.jboot.admin.service.entity.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseInstitutionInfo<M extends BaseInstitutionInfo<M>> extends JbootModel<M> implements IBean {

    public static final String ACTION_ADD = "InstitutionInfo:add";
    public static final String ACTION_DELETE = "InstitutionInfo:delete";
    public static final String ACTION_UPDATE = "InstitutionInfo:update";


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


	public void setInstitutionId(java.lang.String institutionId) {
		set("institution_id", institutionId);
	}
	
	public java.lang.String getInstitutionId() {
		return getStr("institution_id");
	}

	public void setInstitutionName(java.lang.String institutionName) {
		set("institution_name", institutionName);
	}
	
	public java.lang.String getInstitutionName() {
		return getStr("institution_name");
	}

	public void setInstitutionLogo(java.lang.String institutionLogo) {
		set("institution_logo", institutionLogo);
	}
	
	public java.lang.String getInstitutionLogo() {
		return getStr("institution_logo");
	}

	public void setInstitutionWdNum(java.lang.String institutionWdNum) {
		set("institution_wd_num", institutionWdNum);
	}
	
	public java.lang.String getInstitutionWdNum() {
		return getStr("institution_wd_num");
	}

	public void setInstitutionWdId(java.lang.String institutionWdId) {
		set("institution_wd_id", institutionWdId);
	}
	
	public java.lang.String getInstitutionWdId() {
		return getStr("institution_wd_id");
	}

}
