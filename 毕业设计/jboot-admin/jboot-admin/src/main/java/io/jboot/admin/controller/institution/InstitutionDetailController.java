package io.jboot.admin.controller.institution;

import com.jfinal.plugin.activerecord.Page;

import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.InstitutionDetailInfoService;
import io.jboot.admin.service.api.InstitutionInfoService;
import io.jboot.admin.service.entity.model.InstitutionDetailInfo;
import io.jboot.admin.service.entity.model.InstitutionInfo;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/institution/detail")
public class InstitutionDetailController extends BaseController{
	@JbootrpcService 
	private InstitutionDetailInfoService dataService;
	
	@JbootrpcService 
	private InstitutionInfoService infoService;
    /**
     * index
     */
    public void index() {
    	String id = getPara("id");
        setAttr("data", id).render("main.html");
    }

    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        Page<InstitutionDetailInfo> dataPage = dataService.findPage(pageNumber, pageSize);
        renderJson(new DataTable<InstitutionDetailInfo>(dataPage));
    }

    /**
     * add
     */
    public void add() {
        render("add.html");
    }

    /**
     * 保存提交
     */
    public void postAdd() {
    	InstitutionDetailInfo data = getBean(InstitutionDetailInfo.class, "data");
    	InstitutionInfo info= infoService.findById(data.getInstitutionId());
    	String i = info.getInstitutionWdNum();
    	int ii = Integer.valueOf(i);
    	ii++;
    	info.setInstitutionWdNum(String.valueOf(ii));
        if (!dataService.save(data)) {
            throw new BusinessException("保存失败");
        }
        infoService.update(info);
        renderJson(RestResult.buildSuccess());
    }

    /**
     * update
     */
    @NotNullPara({"id"})
    public void update() {
        String id = getPara("id");
        InstitutionDetailInfo data = dataService.findById(id);
        setAttr("data", data).render("update.html");
    }

    /**
     * 修改提交
     */
    public void postUpdate() {
    	InstitutionDetailInfo data = getBean(InstitutionDetailInfo.class, "data");

        if (dataService.findById(data.getWdId()) == null) {
            throw new BusinessException("数据不存在");
        }

        if (!dataService.update(data)) {
            throw new BusinessException("修改失败");
        }
        renderJson(RestResult.buildSuccess());
    }

    /**
     * 删除
     */
    @NotNullPara({"id"})
    public void delete() {
        String id = getPara("id");
        if (!dataService.deleteById(id)) {
            throw new BusinessException("删除失败");
        }

        renderJson(RestResult.buildSuccess());
    }

    public void cache() {
        dataService.refreshCache();
        renderJson(RestResult.buildSuccess());
    }
}
