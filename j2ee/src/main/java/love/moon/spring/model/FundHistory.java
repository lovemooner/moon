package love.moon.spring.model;

import love.moon.spring.model.base.LongIdentifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: lovemooner
 * Date: 2018/9/2 13:35
 */
@Entity
@Table(name = "fund_history")
public class FundHistory extends LongIdentifier {
    private String name;
    private int score;
    private Long fetchDate;
    private Long startDate;
    private Long endDate;
    private Long fundId;
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;
    private String attr5;
    private String attr6;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Column(name = "fetch_date")
    public Long getFetchDate() {
        return fetchDate;
    }

    public void setFetchDate(Long fetchDate) {
        this.fetchDate = fetchDate;
    }

    public Long getFundId() {
        return fundId;
    }

    public void setFundId(Long fundId) {
        this.fundId = fundId;
    }

    @Column(name = "start_date")
    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    @Column(name = "attr1")
    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    @Column(name = "attr2")
    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    @Column(name = "attr3")
    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    @Column(name = "attr4")
    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    @Column(name = "attr5")
    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    @Column(name = "attr6")
    public String getAttr6() {
        return attr6;
    }

    public void setAttr6(String attr6) {
        this.attr6 = attr6;
    }
}
