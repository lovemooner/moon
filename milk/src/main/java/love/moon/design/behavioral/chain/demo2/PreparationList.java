package love.moon.design.behavioral.chain.demo2;

import lombok.Data;

/**
 * @auther dongnan
 * @date 2019/11/23 19:40
 * @describe
 */
@Data
public class PreparationList {

    private boolean washFace;
    private boolean washHair;
    private boolean haveBreakfast;

    @Override
    public String toString() {
        return "ThingList [washFace=" + washFace + ", washHair=" + washHair + ", haveBreakfast=" + haveBreakfast + "]";
    }

}
