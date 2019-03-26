package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.ServiceOrderService;
import io.jboot.admin.service.entity.model.ServiceOrder;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

import com.jfinal.plugin.activerecord.Page;

@Bean
@Singleton
@JbootrpcService
public class ServiceOrderServiceImpl extends JbootServiceBase<ServiceOrder> implements ServiceOrderService {

	@Override
	public Page<ServiceOrder> findPage(int pageNumber, int pageSize) {
	    Columns columns = Columns.create();
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
    }

	@Override
    public void refreshCache() {
        Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
    }

}