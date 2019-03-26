package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.WarehouseInfoService;
import io.jboot.admin.service.entity.model.WarehouseInfo;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

import com.jfinal.plugin.activerecord.Page;

@Bean
@Singleton
@JbootrpcService
public class WarehouseInfoServiceImpl extends JbootServiceBase<WarehouseInfo> implements WarehouseInfoService {

	@Override
	public Page<WarehouseInfo> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
	       Columns columns = Columns.create();
	        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
	    }
		@Override
	    public void refreshCache() {
	        Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
	    }


}