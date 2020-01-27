package love.moon.spring.common;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/12/4 15:43
 */
public class Menu {
    private Long menuId;
    private Long parentId;
    private String label;
    private String href;
    private Integer grade;
    private Integer sort;
    private String menuName;
    private List<Menu> children;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }


    public void sort() {
        Collections.sort(this.getChildren(), new Comparator<Menu>() {
            public int compare(Menu m1, Menu m2) {
                if (m1.getSort() == null) {
                    return -1;
                }
                if (m2.getSort() == null) {
                    return 1;
                }
                try {
                    return m1.getSort().compareTo(m2.getSort());
                } catch (Exception e) {
                    return -1;
                }
            }
        });
        if (CollectionUtils.isEmpty(this.getChildren())) {
            return;
        }
        for (Menu child : this.getChildren()) {
            child.sort();
        }
    }


    public List<Menu> getChild(long menuId,List<Menu> menuList) {
        List<Menu> childList = new ArrayList<Menu>();
        for (Menu menu : menuList) {
            if (menu.getParentId().equals(menuId)) {
                childList.add(menu);
            }
        }
        //递归终止条件
        if (childList.size()==0) {
            return null;
        }
        //开始递归
        for (Menu menu : childList) {
            menu.setChildren(menu.getChild(menu.getMenuId(),menuList));
        }
        return childList;
    }

    public void buildMenu() {
        List<Menu> allMenus = new ArrayList<Menu>();//dao.getAllMenus();
        List<Menu> parents = new ArrayList<Menu>();
        //find all 1 level menu
        for (Menu menu : allMenus) {
            if (menu.getParentId() == null) {
                parents.add(menu);
            }
        }
        for (Menu menu : parents) {
            menu.setChildren(getChild(menu.getMenuId(),allMenus));
        }

    }

}
