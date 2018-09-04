package love.moon.spring.model;

import java.util.List;

/**
 * Author: lovemooner
 * Date: 2018/9/2 15:46
 */
public class FundDTO {

    private List<Fund> funds;
    private List<FundHistory> fundHistories;

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }

    public List<FundHistory> getFundHistories() {
        return fundHistories;
    }

    public void setFundHistories(List<FundHistory> fundHistories) {
        this.fundHistories = fundHistories;
    }
}
