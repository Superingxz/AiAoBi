package xinlan.com.AiAoBi.entity;

/**
 * Created by Administrator on 2017/2/14.
 */
public class RankingInfo {
    private String name;
    private String ranking;
    private float counts;

    public RankingInfo(String name, String ranking, float counts) {
        this.name = name;
        this.ranking = ranking;
        this.counts = counts;
    }

    public String getName() {
        return name;
    }

    public String getRanking() {
        return ranking;
    }

    public float getCounts() {
        return counts;
    }
}
