package com.example.demo.model;


import com.example.demo.model.enums.NotifyStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @NotNull
    @Size(min = 2,message = "Message must be min 2 characters")
    private String message;

    @NotNull
    @Enumerated
    private NotifyStatus notifyStatus;

    @NotNull
    private long notificationDate;

    private boolean sendEmail;

    private boolean sendSms;

    private boolean sendPush;

    private boolean notified;


    @ManyToOne
    @JsonIgnore
    private User user;


    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotifyStatus getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(NotifyStatus notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public long getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(long notificationDate) {
        this.notificationDate = notificationDate;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public boolean isSendSms() {
        return sendSms;
    }

    public void setSendSms(boolean sendSms) {
        this.sendSms = sendSms;
    }

    public boolean isSendPush() {
        return sendPush;
    }

    public void setSendPush(boolean sendPush) {
        this.sendPush = sendPush;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return id == that.id &&
                notificationDate == that.notificationDate &&
                sendEmail == that.sendEmail &&
                sendSms == that.sendSms &&
                sendPush == that.sendPush &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(message, that.message) &&
                notifyStatus == that.notifyStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, message, notifyStatus, notificationDate, sendEmail, sendSms, sendPush);
    }


    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", message='" + message + '\'' +
                ", notifyStatus=" + notifyStatus +
                ", notificationDate=" + notificationDate +
                ", sendEmail=" + sendEmail +
                ", sendSms=" + sendSms +
                ", sendPush=" + sendPush +
                '}';
    }
}
