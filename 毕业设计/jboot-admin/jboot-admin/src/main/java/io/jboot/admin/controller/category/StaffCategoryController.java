package io.jboot.admin.controller.category;

import java.util.Date;

import com.jfinal.plugin.activerecord.Page;

import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.DataService;
import io.jboot.admin.service.api.StaffCategoryService;
import io.jboot.admin.service.entity.model.Data;
import io.jboot.admin.service.entity.model.StaffCategory;
import io.jboot.admin.service.entity.status.system.DataStatus;
import io.jboot.admin.support.auth.AuthUtils;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/category/staffcategory")
public class StaffCategoryController extends BaseController{

    @JbootrpcService
    private StaffCategoryService dataService;

    /**
     * index
     */
    public void index() {
        render("main.html");
    }

    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        Page<StaffCategory> dataPage = dataService.findPage(pageNumber, pageSize);
        renderJson(new DataTable<StaffCategory>(dataPage));
    }

  

}
