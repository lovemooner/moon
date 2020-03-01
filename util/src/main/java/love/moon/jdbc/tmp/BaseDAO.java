package love.moon.jdbc.tmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: lovemooner
 * Date: 17-4-18
 * Time: 下午1:01
 */
public class BaseDAO<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BaseDAO.class);
    DBConn conn = new DBConn();
    private Connection connection = null;

    @SuppressWarnings("unused")
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public BaseDAO() {
        //获得参数化类型
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        persistentClass = (Class<T>) type.getActualTypeArguments()[0];
    }


    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = conn.getConnection();
        }
        return connection;
    }


    public void save(T entity) throws Exception {
        PreparedStatement pstmt = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("insert into " + getTableName(entity) + "(");
            List<Method> list = this.matchPojoMethods(entity, "get");
            Iterator<Method> iter = list.iterator();
            //拼接字段顺序 insert into table name(id,name,email,
            while (iter.hasNext()) {
                Method method = iter.next();
                sb.append(getColumnName(method) + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") values(");
            //拼装预编译SQL语句insert insert into table name(id,name,email) values(?,?,?,
            for (int j = 0; j < list.size(); j++) {
                sb.append("?,");
            }
            //去掉SQL语句最后一个,符号insert insert into table name(id,name,email) values(?,?,?);
            sb.deleteCharAt(sb.length() - 1).append(")");
            //到此SQL语句拼接完成,打印SQL语句
            LOG.info(sb.toString());
            //获得预编译对象的引用
            PreparedStatement statement = getConnection().prepareStatement(sb.toString());
            int i = 0;
            //把指向迭代器最后一行的指针移到第一行.
            iter = list.iterator();
            while (iter.hasNext()) {
                Method method = iter.next();
                //此初判断返回值的类型,因为存入数据库时有的字段值格式需要改变,比如String,SQL语句是'"+abc+"'
                if (method.getReturnType().getSimpleName().indexOf("String") != -1) {
                    statement.setString(++i, this.getString(method, entity));
                } else if (method.getReturnType().getSimpleName().indexOf("Date") != -1) {
                    statement.setDate(++i, this.getDate(method, entity));
                } else if (method.getReturnType().getSimpleName().indexOf("InputStream") != -1) {
                    statement.setAsciiStream(++i, this.getBlob(method, entity), 1440);
                } else {
                    statement.setInt(++i, this.getInt(method, entity));
                }
            }
            conn.execOther(statement);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void update(T entity) throws Exception {
        PreparedStatement pstmt = null;
        try {
            String sql = "update " + getTableName(entity) + " set ";

            //获得该类所有get方法对象集合
            List<Method> list = this.matchPojoMethods(entity, "get");

            //临时Method对象,负责迭代时装method对象.
            Method tempMethod = null;

            //由于修改时不需要修改ID,所以按顺序加参数则应该把Id移到最后.
            Method idMethod = null;
            Iterator<Method> iter = list.iterator();
            while (iter.hasNext()) {
                tempMethod = iter.next();
                //如果方法名中带有ID字符串并且长度为2,则视为ID.
                if (tempMethod.getName().lastIndexOf("Id") != -1 && tempMethod.getName().substring(3).length() == 2) {
                    //把ID字段的对象存放到一个变量中,然后在集合中删掉.
                    idMethod = tempMethod;
                    iter.remove();
                    //如果方法名去掉set/get字符串以后与pojo + "id"想符合(大小写不敏感),则视为ID
                } else if ((entity.getClass().getSimpleName() + "Id").equalsIgnoreCase(tempMethod.getName().substring(3))) {
                    idMethod = tempMethod;
                    iter.remove();
                }
            }

            //把迭代指针移到第一位
            iter = list.iterator();
            while (iter.hasNext()) {
                tempMethod = iter.next();
                sql += tempMethod.getName().substring(3).toLowerCase() + "= ?,";
            }

            //去掉最后一个,符号
            sql = sql.substring(0, sql.lastIndexOf(","));

            //添加条件
            sql += " where " + idMethod.getName().substring(3).toLowerCase() + " = ?";

            //SQL拼接完成,打印SQL语句
            LOG.info(sql);
            pstmt = this.getConnection().prepareStatement(sql);

            int i = 0;
            iter = list.iterator();
            while (iter.hasNext()) {
                Method method = iter.next();
                //此初判断返回值的类型,因为存入数据库时有的字段值格式需要改变,比如String,SQL语句是'"+abc+"'
                if (method.getReturnType().getSimpleName().indexOf("String") != -1) {
                    pstmt.setString(++i, this.getString(method, entity));
                } else if (method.getReturnType().getSimpleName().indexOf("Date") != -1) {
                    pstmt.setDate(++i, this.getDate(method, entity));
                } else if (method.getReturnType().getSimpleName().indexOf("InputStream") != -1) {
                    pstmt.setAsciiStream(++i, this.getBlob(method, entity), 1440);
                } else {
                    pstmt.setInt(++i, this.getInt(method, entity));
                }
            }
            //为Id字段添加值
            if (idMethod.getReturnType().getSimpleName().indexOf("String") != -1) {
                pstmt.setString(++i, this.getString(idMethod, entity));
            } else {
                pstmt.setInt(++i, this.getInt(idMethod, entity));
            }
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public T findById(Object object) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        T entity = null;
        try {
            //通过子类的构造函数,获得参数化类型的具体类型.比如BaseDAO<T>也就是获得T的具体类型
            T entityClass = persistentClass.newInstance();
            String sql = "select * from " + getTableName(entityClass) + " where ";
            //存放Pojo(或被操作表)主键的方法对象
            Method idMethod = null;
            List<Method> list = this.matchPojoMethods(entityClass, "set");
            Iterator<Method> iter = list.iterator();
            //过滤取得Method对象
            while (iter.hasNext()) {
                Method tempMethod = iter.next();
                if (tempMethod.getName().indexOf("Id") != -1 && tempMethod.getName().substring(3).length() == 2) {
                    idMethod = tempMethod;
                } else if ((entityClass.getClass().getSimpleName() + "Id").equalsIgnoreCase(tempMethod.getName().substring(3))) {
                    idMethod = tempMethod;
                }
            }
            //第一个字母转为小写
            sql += idMethod.getName().substring(3, 4).toLowerCase() + idMethod.getName().substring(4) + " = ?";
            LOG.info(sql);
            //获得连接
            pstmt = this.getConnection().prepareStatement(sql);
            //判断id的类型
            if (object instanceof Integer) {
                pstmt.setInt(1, (Integer) object);
            } else if (object instanceof String) {
                pstmt.setString(1, (String) object);
            }
            rs = conn.execQuery(pstmt);
            //把指针指向迭代器第一行
            iter = list.iterator();
            while (rs.next()) {
                entity = persistentClass.newInstance();
                while (iter.hasNext()) {
                    Method method = iter.next();
                    if (method.getParameterTypes()[0].getSimpleName().indexOf("String") != -1) {
                        //由于list集合中,method对象取出的方法顺序与数据库字段顺序不一致(比如:list的第一个方法是setDate,而数据库按顺序取的是"123"值)
                        //所以数据库字段采用名字对应的方式取.
                        this.setString(method, entity, rs.getString(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getSimpleName().indexOf("Date") != -1) {
                        this.setDate(method, entity, rs.getDate(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getSimpleName().indexOf("InputStream") != -1) {
                        this.setBlob(method, entity, rs.getBlob(method.getName().substring(3).toLowerCase()).getBinaryStream());
                    } else {
                        this.setInt(method, entity, rs.getInt(method.getName().substring(3).toLowerCase()));
                    }
                }
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }


    public List<T> findAll() throws Exception {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        List<T> list = null;
        try {
            T obj = persistentClass.newInstance();
            String sql = "select * from " + getTableName(obj);
            LOG.info(sql);
            //获得连接
            pstmt = this.getConnection().prepareStatement(sql);
            //执行sql,取得查询结果集.
            rs = conn.execQuery(pstmt);
            list = new ArrayList<T>();
            List<Method> mList = this.matchPojoMethods(obj, "set");
            while (rs.next()) {
                T entity = persistentClass.newInstance();
                Iterator<Method> iterator = mList.iterator();
                while (iterator.hasNext()) {
                    Method method = iterator.next();
                    if (method.getParameterTypes()[0].getSimpleName().indexOf("String") != -1) {
                        //由于list集合中,method对象取出的方法顺序与数据库字段顺序不一致(比如:list的第一个方法是setDate,而数据库按顺序取的是"123"值)
                        //所以数据库字段采用名字对应的方式取.
                        this.setString(method, entity, rs.getString(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getSimpleName().indexOf("Date") != -1) {
                        this.setDate(method, entity, rs.getDate(method.getName().substring(3).toLowerCase()));
                    } else if (method.getParameterTypes()[0].getSimpleName().indexOf("InputStream") != -1) {
                        this.setBlob(method, entity, rs.getBlob(method.getName().substring(3).toLowerCase()).getBinaryStream());
                    } else {
                        this.setInt(method, entity, rs.getInt(method.getName().substring(3).toLowerCase()));
                    }
                }
                list.add(entity);
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


    public void delete(T entity) throws Exception {
        PreparedStatement pstmt = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM " + getTableName(entity) + " WHERE ");
            Method idMethod = matchPojoIdMethod(entity);
            sb.append(getColumnName(idMethod) + " = ?");
            LOG.info(sb.toString());
            pstmt = this.getConnection().prepareStatement(sb.toString());
            int i = 0;
            if (idMethod.getReturnType().getSimpleName().indexOf("String") != -1) {
                pstmt.setString(++i, this.getString(idMethod, entity));
            } else {
                pstmt.setInt(++i, this.getInt(idMethod, entity));
            }
            conn.execOther(pstmt);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private String getTableName(T entity) {
        Table table = entity.getClass().getAnnotation(Table.class);
        return table != null ? table.name() : entity.getClass().getSimpleName().toLowerCase();
    }

    private String getColumnName(Method method) {
        Column column = method.getAnnotation(Column.class);
        return column != null ? column.name() : method.getName().substring(3).toLowerCase();
    }

    private Method matchPojoIdMethod(T entity) {
        Method idMethod = null;
        Method[] methods = entity.getClass().getDeclaredMethods();
        for (int index = 0; index < methods.length; ++index) {
            if (methods[index].getAnnotation(Id.class) != null) {
                idMethod = methods[index];
                break;
            }
        }
        if (idMethod == null) {
            List<Method> list = this.matchPojoMethods(entity, "get");
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Method tempMethod = (Method) iter.next();
                if (tempMethod.getName().lastIndexOf("Id") != -1 && tempMethod.getName().substring(3).length() == 2) {
                    idMethod = tempMethod;
                    break;
                } else if ((entity.getClass().getSimpleName() + "Id").equalsIgnoreCase(tempMethod.getName().substring(3))) {
                    idMethod = tempMethod;
                    break;
                }
            }
        }
        return idMethod;
    }

    /**
     * 过滤当前Pojo类所有带传入字符串的Method对象,返回List集合.
     *
     * @param entity
     * @param methodName
     * @return
     */
    private List<Method> matchPojoMethods(T entity, String methodName) {
        Method[] methods = entity.getClass().getDeclaredMethods();
        List<Method> list = new ArrayList<Method>();
        //过滤当前Pojo类所有带get字符串的Method对象,存入List容器
        for (int index = 0; index < methods.length; index++) {
            if (methods[index].getName().indexOf(methodName) != -1) {
                list.add(methods[index]);
            }
        }
        return list;
    }


    /**
     * 方法返回类型为int或Integer类型时,返回的SQL语句值.对应get
     *
     * @param method
     * @param entity
     * @return
     * @throws Exception
     */
    public Integer getInt(Method method, T entity) throws Exception {
        return (Integer) method.invoke(entity, new Object[]{});
    }

    /**
     * 方法返回类型为String时,返回的SQL语句拼装值.比如'abc',对应get
     *
     * @param method
     * @param entity
     * @return
     * @throws Exception
     */
    public String getString(Method method, T entity) throws Exception {
        return (String) method.invoke(entity, new Object[]{});
    }

    /**
     * 方法返回类型为Blob时,返回的SQL语句拼装值.对应get
     *
     * @param method
     * @param entity
     * @return
     * @throws Exception
     */
    public InputStream getBlob(Method method, T entity) throws Exception {
        return (InputStream) method.invoke(entity, new Object[]{});
    }


    /**
     * 方法返回类型为Date时,返回的SQL语句拼装值,对应get
     *
     * @param method
     * @param entity
     * @return
     * @throws Exception
     */
    public Date getDate(Method method, T entity) throws Exception {
        return (Date) method.invoke(entity, new Object[]{});
    }


    /**
     * 参数类型为Integer或int时,为entity字段设置参数,对应set
     *
     * @param method
     * @param entity
     * @param arg
     * @return
     * @throws Exception
     */
    public Integer setInt(Method method, T entity, Integer arg) throws Exception {
        return (Integer) method.invoke(entity, new Object[]{arg});
    }

    /**
     * 参数类型为String时,为entity字段设置参数,对应set
     *
     * @param method
     * @param entity
     * @param arg
     * @return
     * @throws Exception
     */
    public String setString(Method method, T entity, String arg) throws Exception {
        return (String) method.invoke(entity, new Object[]{arg});
    }

    /**
     * 参数类型为InputStream时,为entity字段设置参数,对应set
     *
     * @param method
     * @param entity
     * @param arg
     * @return
     * @throws Exception
     */
    public InputStream setBlob(Method method, T entity, InputStream arg) throws Exception {
        return (InputStream) method.invoke(entity, new Object[]{arg});
    }


    /**
     * 参数类型为Date时,为entity字段设置参数,对应set
     *
     * @param method
     * @param entity
     * @param arg
     * @return
     * @throws Exception
     */
    public Date setDate(Method method, T entity, Date arg) throws Exception {
        return (Date) method.invoke(entity, new Object[]{arg});
    }
}
