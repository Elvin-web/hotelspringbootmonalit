package az.elvin.hotel.model;

public class AdvancedSearch {
    private Long roomTypeId;
    private String beginDate;
    private String endDate;
    private Long guestId;
    private Long paymentStatusId;
    private Long bookingStatusId;
    private String ID;

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(Long paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public Long getBookingStatusId() {
        return bookingStatusId;
    }

    public void setBookingStatusId(Long bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "AdvancedSearch{" +
                "roomTypeId=" + roomTypeId +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", guestId=" + guestId +
                ", paymentStatusId=" + paymentStatusId +
                ", bookingStatusId=" + bookingStatusId +
                ", ID='" + ID + '\'' +
                '}';
    }
}
