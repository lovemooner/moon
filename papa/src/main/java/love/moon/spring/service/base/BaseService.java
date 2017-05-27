package love.moon.spring.service.base;

import love.moon.spring.dao.BaseDao;
import love.moon.spring.model.base.LongIdentifier;

/**
 * Author: lovemooner
 * Date: 2017/5/22 17:37
 */
public abstract class BaseService<T extends LongIdentifier> implements IBaseService<T> {

    public abstract BaseDao<T> getDAO();

    @Override
    public void save(T t) {
        getDAO().save(t);
    }

    @Override
    public void delete(T t) {
        getDAO().delete(t);
    }

    @Override
    public void merge(T t) {
        getDAO().merge(t);
    }

    @Override
    public void update(T t) {
        getDAO().update(t);
    }

    @Override
    public T getById(Long id) {
        return getDAO().getById(id);
    }
}

