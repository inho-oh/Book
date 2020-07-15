package usedbookstore;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Book_table")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer qty;
    private Long price;

    @PostPersist
    public void onPostPersist(){
        BookRegistered bookRegistered = new BookRegistered();
        BeanUtils.copyProperties(this, bookRegistered);
        bookRegistered.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        StatusManaged statusManaged = new StatusManaged();
        BeanUtils.copyProperties(this, statusManaged);
        statusManaged.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }




}
