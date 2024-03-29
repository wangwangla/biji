package io.jboot.admin.service.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.service.entity.model.WaregouseInto;

import java.util.List;

public interface WaregouseIntoService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public WaregouseInto findById(Object id);


    /**
     * find all model
     *
     * @return all <WaregouseInto
     */
    public List<WaregouseInto> findAll();


    /**
     * delete model by primary key
     *
     * @param id
     * @return success
     */
    public boolean deleteById(Object id);


    /**
     * delete model
     *
     * @param model
     * @return
     */
    public boolean delete(WaregouseInto model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(WaregouseInto model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(WaregouseInto model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(WaregouseInto model);


    public void join(Page<? extends Model> page, String joinOnField);
    public void join(Page<? extends Model> page, String joinOnField, String[] attrs);
    public void join(Page<? extends Model> page, String joinOnField, String joinName);
    public void join(Page<? extends Model> page, String joinOnField, String joinName, String[] attrs);
    public void join(List<? extends Model> models, String joinOnField);
    public void join(List<? extends Model> models, String joinOnField, String[] attrs);
    public void join(List<? extends Model> models, String joinOnField, String joinName);
    public void join(List<? extends Model> models, String joinOnField, String joinName, String[] attrs);
    public void join(Model model, String joinOnField);
    public void join(Model model, String joinOnField, String[] attrs);
    public void join(Model model, String joinOnField, String joinName);
    public void join(Model model, String joinOnField, String joinName, String[] attrs);

    public void keep(Model model, String... attrs);
    public void keep(List<? extends Model> models, String... attrs);


	public void refreshCache();


	public Page<WaregouseInto> findPage(int pageNumber, int pageSize);


	public Page<WaregouseInto> findPage(WaregouseInto waregouseInto, int pageNumber, int pageSize);
}