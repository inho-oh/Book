
package usedbookstore;

import java.util.Date;


public class Purchased extends AbstractEvent {

    private Long id;
    private Long bookid;
    private Long customerid;
    private Integer qty;
    private Date purchasedate;
    private Date canceldate;
    private Integer point;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }
    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public Date getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }
    public Date getCanceldate() {
        return canceldate;
    }

    public void setCanceldate(Date canceldate) {
        this.canceldate = canceldate;
    }
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
