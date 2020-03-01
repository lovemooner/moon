package love.moon.spring.model;

import love.moon.spring.dao.HomeIPDto;
import love.moon.util.DateUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "home_ip")
public class HomeIP {
    private Long id;
    private Long created;
    private Long lastUpdate;
    private String ip;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "created")
    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Column(name = "lastUpdate")
    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public HomeIPDto toDTO(){
        HomeIPDto dto=new HomeIPDto();
        dto.setCreated(DateUtil.convertDateLongToString(getCreated(),DateUtil.ALL));
        dto.setLastUpdate(DateUtil.convertDateLongToString(getLastUpdate(),DateUtil.ALL));
        dto.setIp(getIp());
        return dto;
    }

}
