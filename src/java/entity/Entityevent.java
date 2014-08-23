/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "entityevent")
@NamedQueries({
    @NamedQuery(name = "Entityevent.findAll", query = "SELECT e FROM Entityevent e"),
    @NamedQuery(name = "Entityevent.findByEventID", query = "SELECT e FROM Entityevent e WHERE e.eventID = :eventID"),
    @NamedQuery(name = "Entityevent.findByHostID", query = "SELECT e FROM Entityevent e WHERE e.hostID = :hostID"),
    @NamedQuery(name = "Entityevent.findByCreator", query = "SELECT e FROM Entityevent e WHERE e.creator = :creator"),
    @NamedQuery(name = "Entityevent.findByDescprition", query = "SELECT e FROM Entityevent e WHERE e.descprition = :descprition"),
    @NamedQuery(name = "Entityevent.findByStartTime", query = "SELECT e FROM Entityevent e WHERE e.startTime = :startTime"),
    @NamedQuery(name = "Entityevent.findByEndTime", query = "SELECT e FROM Entityevent e WHERE e.endTime = :endTime"),
    @NamedQuery(name = "Entityevent.findByEventType", query = "SELECT e FROM Entityevent e WHERE e.eventType = :eventType"),
    @NamedQuery(name = "Entityevent.findByPictureSmallURL", query = "SELECT e FROM Entityevent e WHERE e.pictureSmallURL = :pictureSmallURL"),
    @NamedQuery(name = "Entityevent.findByPictureURL", query = "SELECT e FROM Entityevent e WHERE e.pictureURL = :pictureURL"),
    @NamedQuery(name = "Entityevent.findByPictureBigURL", query = "SELECT e FROM Entityevent e WHERE e.pictureBigURL = :pictureBigURL"),
    @NamedQuery(name = "Entityevent.findByUpdateTime", query = "SELECT e FROM Entityevent e WHERE e.updateTime = :updateTime"),
    @NamedQuery(name = "Entityevent.findByMedalCost", query = "SELECT e FROM Entityevent e WHERE e.medalCost = :medalCost")})
public class Entityevent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EventID")
    private Integer eventID;
    @Column(name = "HostID")
    private Integer hostID;
    @Column(name = "Creator")
    private Integer creator;
    @Column(name = "Descprition")
    private String descprition;
    @Column(name = "StartTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "EndTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "EventType")
    private Integer eventType;
    @Column(name = "PictureSmallURL")
    private String pictureSmallURL;
    @Column(name = "PictureURL")
    private String pictureURL;
    @Column(name = "PictureBigURL")
    private String pictureBigURL;
    @Column(name = "UpdateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "MedalCost")
    private Integer medalCost;

    public Entityevent() {
    }

    public Entityevent(Integer eventID) {
        this.eventID = eventID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Integer getHostID() {
        return hostID;
    }

    public void setHostID(Integer hostID) {
        this.hostID = hostID;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getDescprition() {
        return descprition;
    }

    public void setDescprition(String descprition) {
        this.descprition = descprition;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getPictureSmallURL() {
        return pictureSmallURL;
    }

    public void setPictureSmallURL(String pictureSmallURL) {
        this.pictureSmallURL = pictureSmallURL;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getPictureBigURL() {
        return pictureBigURL;
    }

    public void setPictureBigURL(String pictureBigURL) {
        this.pictureBigURL = pictureBigURL;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMedalCost() {
        return medalCost;
    }

    public void setMedalCost(Integer medalCost) {
        this.medalCost = medalCost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventID != null ? eventID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entityevent)) {
            return false;
        }
        Entityevent other = (Entityevent) object;
        if ((this.eventID == null && other.eventID != null) || (this.eventID != null && !this.eventID.equals(other.eventID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Entityevent[eventID=" + eventID + "]";
    }

}
