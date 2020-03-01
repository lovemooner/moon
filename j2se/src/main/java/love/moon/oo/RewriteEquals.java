package love.moon.oo;

public class RewriteEquals {

    private Integer id;

    public RewriteEquals(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof RewriteEquals)) {
            return false;
        } else {
            return this.getId().equals(((RewriteEquals) o).getId());
        }
    }

    public int hashCode() {
        return id.hashCode();
    }
}
