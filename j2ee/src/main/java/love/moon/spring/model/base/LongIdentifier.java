package love.moon.spring.model.base;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.CallbackException;
import org.hibernate.Session;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


/**
 * Created by nadong on 2017/4/21.
 */
@MappedSuperclass
@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, updatable = false))
public abstract class LongIdentifier extends GeneratedIdentifier<Long> {
    protected LongIdentifier() {
    }

    protected LongIdentifier(long createTime) {
        super(createTime);
    }

    public boolean onSave(Session s) throws CallbackException {
        return false;
    }

    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).toString();
    }
}
