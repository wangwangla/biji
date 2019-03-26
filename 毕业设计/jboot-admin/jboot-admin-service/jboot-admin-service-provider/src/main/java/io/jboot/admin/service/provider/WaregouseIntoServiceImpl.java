package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.WaregouseIntoService;
import io.jboot.admin.service.entity.model.WaregouseInto;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

import com.jfinal.plugin.activerecord.Page;

@Bean
@Singleton
@JbootrpcService
public class WaregouseIntoServiceImpl extends JbootServiceBase<WaregouseInto> implements WaregouseIntoService {

	@Override
    public void refreshCache() {
        Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
    }


	@Override
	public Page<WaregouseInto> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		  Columns columns = Columns.create();
	        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
	    }

}