package io.jboot.admin.controller.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.upload.UpLoadUtils;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.ServiceOrderEnclosureService;
import io.jboot.admin.service.api.ServiceOrderService;
import io.jboot.admin.service.entity.model.Enclosure;
import io.jboot.admin.service.entity.model.InstitutionInfo;
import io.jboot.admin.service.entity.model.ServiceOrder;
import io.jboot.admin.service.entity.model.ServiceOrderEnclosure;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/service/order")
public class ServiceOrderController extends BaseController{
	@JbootrpcService
	private ServiceOrderService dataService;
	private static List<String> listImage = new ArrayList<String>();
	
	@JbootrpcService
	private ServiceOrderEnclosureService service;
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
        Page<ServiceOrder> dataPage = dataService.findPage(pageNumber, pageSize);
        renderJson(new DataTable<ServiceOrder>(dataPage));
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
    	ServiceOrder data = getBean(ServiceOrder.class, "serviceOrder");
        data.setServiceOrderStartTime(new Date());
        String id =  UUID.randomUUID().toString().substring(0, 20);
        data.setServiceOrderId(id);
        data.setOrderStatus("创建处理");
        if (!dataService.save(data)) {
            throw new BusinessException("保存失败");
        }
        for(String s : listImage) {
          	 ServiceOrderEnclosure enclosure = new ServiceOrderEnclosure();
          	 enclosure.setOrderEnclosurePhoto(s);
          	 enclosure.setOrderEnclosureUpTime(id);
          	 service.save(enclosure);
           }
        listImage.clear();
        renderJson(RestResult.buildSuccess());
    }

    /**
     * update
     */
    @NotNullPara({"id"})
    public void update() {
        String id = getPara("id");
        ServiceOrder data = dataService.findById(id);
        setAttr("data", data).render("update.html");
    }

    /**
     * 修改提交
     */
    public void postUpdate() {
    	ServiceOrder data = getBean(ServiceOrder.class, "data");

        if (dataService.findById(data.getServiceOrderId()) == null) {
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
    /**
     * 上传
     */
    public void postUpload() throws Exception{
   	 HashMap<String, UploadFile> HashMapFile = getUploadFilesMap();
   	 UploadFile file = HashMapFile.get("file");
   	 listImage = UpLoadUtils.uploadFile(file, listImage);
   	 renderJson(RestResult.buildSuccess());
    }
    
    public void hetong() {
   	 String id  = getPara("id");
   	 setAttr("data", id);
        render("hetong.html");
    }
    
    public void load() {
   	 String id = getPara("id");
   	 Page<ServiceOrderEnclosure> dataPage = service.findByCon(id); 
   	 renderJson(new DataTable<>(dataPage));
    }
    
  
}
