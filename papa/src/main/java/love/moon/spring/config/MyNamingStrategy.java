package love.moon.spring.config;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * Author: lovemooner
 * Date: 2017/5/19 13:27
 */
public class MyNamingStrategy extends ImprovedNamingStrategy {
    private String tablePrefix = "CA_";
    private String columnPrefix = "";
    private boolean toUpperCase = false;

    public void setToUpperCase(boolean toUpperCase) {
        this.toUpperCase = toUpperCase;
    }

    /* (non-Javadoc)
         * @see org.hibernate.cfg.ImprovedNamingStrategy#propertyToColumnName(java.lang.String)
         * @param propertyName
         * @return
         *
         */
    @Override
    public String propertyToColumnName(String propertyName) {
        // TODO Auto-generated method stub
        //return super.propertyToColumnName(propertyName);
        if (toUpperCase) {
            return (this.columnPrefix + super.propertyToColumnName(propertyName)).toUpperCase();
        } else {
            return (this.columnPrefix + super.propertyToColumnName(propertyName));
        }
    }

    /* (non-Javadoc)
     * @see org.hibernate.cfg.ImprovedNamingStrategy#classToTableName(java.lang.String)
     * @param className
     * @return
     *
     */
    @Override
    public String classToTableName(String className) {
        // TODO Auto-generated method stub
        //return super.classToTableName(className);
        if (toUpperCase) {
            System.out.println("toUpperCase");
            return (this.tablePrefix + super.classToTableName(className)).toUpperCase();
        } else {
            return (this.tablePrefix + super.classToTableName(className));
        }
    }

}