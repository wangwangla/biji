package io.jboot.admin.service.provider;


import io.jboot.admin.service.api.CommodityInfoService;
import io.jboot.admin.service.entity.model.CommodityInfo;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.service.JbootServiceBase;

import java.util.List;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class CommodityInfoServiceImpl extends JbootServiceBase<CommodityInfo> implements CommodityInfoService {

	@Override
	public Page<CommodityInfo> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		
		return DAO.paginate(pageNumber, pageSize);
	}

	@Override
	public List<CommodityInfo> findByName(String name) {
		// TODO Auto-generated method stub
		Columns columns = new Columns();
		columns.eq("", name);
		return DAO.findListByColumns(columns);
	}

	@Override
	public Page<? extends Model> paginate(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}