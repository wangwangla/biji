package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.InstitutionDetailInfoService;
import io.jboot.admin.service.entity.model.InstitutionDetailInfo;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

@Bean
@Singleton
@JbootrpcService
public class InstitutionDetailInfoServiceImpl extends JbootServiceBase<InstitutionDetailInfo> implements InstitutionDetailInfoService {



	@Override
	public Page<InstitutionDetailInfo> findPage(int pageNumber, int pageSize) {
	       Columns columns = Columns.create();
	        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
	    }

		@Override
	    public void refreshCache() {
	        Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
	    }

		@Override
		public void delete(String institutionId) {
			// TODO Auto-generated method stub
			Db.delete("delete from institution_detail_info where institution_id='"+institutionId+"'");
		}

}