package love.moon.spring.service.base;

import love.moon.spring.model.base.LongIdentifier;

/**
 * Author: lovemooner
 * Date: 2017/5/22 17:38
 */
public interface IBaseService<T extends LongIdentifier> {
    void save(T t);

    void delete(T t);

    void merge(T t);

    void update(T t);

    T getById(Long id);
}