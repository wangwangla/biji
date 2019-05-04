package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.WaregouseOutService;
import io.jboot.admin.service.entity.model.StaffInfo;
import io.jboot.admin.service.entity.model.WaregouseOut;
import io.jboot.service.JbootServiceBase;

import java.util.List;

import javax.inject.Singleton;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@Bean
@Singleton
@JbootrpcService
public class WaregouseOutServiceImpl extends JbootServiceBase<WaregouseOut> implements WaregouseOutService {

	@Override
	public Page<WaregouseOut> findPage( int pageNumber, int pageSize) {
        Columns columns = Columns.create();
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
    }

	@Override
    public void refreshCache() {
        Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
    }

	@Override
	public List<Record> export() {
		// TODO Auto-generated method stub
		return Db.find("select * from waregouse_out ");
	}

	@Override
	public Page<WaregouseOut> findPage(WaregouseOut waregouseOut, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
	       Columns columns = Columns.create();
	       if(StrKit.notBlank(waregouseOut.getEquipName())) {
	    	   columns.eq("equip_name", waregouseOut.getEquipName());
	       }
	       if(StrKit.notBlank(waregouseOut.getEquipType())) {
	    	   columns.eq("equip_type", waregouseOut.getEquipType());
	       }
	       if(StrKit.notBlank(waregouseOut.getEquipXinghao())) {
	    	   columns.eq("equip_xinghao", waregouseOut.getEquipXinghao());
	       }
	       return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
	}

}