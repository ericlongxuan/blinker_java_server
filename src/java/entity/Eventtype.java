/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "eventtype")
@NamedQueries({
    @NamedQuery(name = "Eventtype.findAll", query = "SELECT e FROM Eventtype e"),
    @NamedQuery(name = "Eventtype.findByEventTypeID", query = "SELECT e FROM Eventtype e WHERE e.eventTypeID = :eventTypeID"),
    @NamedQuery(name = "Eventtype.findByEventTypeProfile", query = "SELECT e FROM Eventtype e WHERE e.eventTypeProfile = :eventTypeProfile")})
public class Eventtype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EventTypeID")
    private Integer eventTypeID;
    @Column(name = "EventTypeProfile")
    private String eventTypeProfile;

    public Eventtype() {
    }

    public Eventtype(Integer eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public Integer getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(Integer eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public String getEventTypeProfile() {
        return eventTypeProfile;
    }

    public void setEventTypeProfile(String eventTypeProfile) {
        this.eventTypeProfile = eventTypeProfile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventTypeID != null ? eventTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventtype)) {
            return false;
        }
        Eventtype other = (Eventtype) object;
        if ((this.eventTypeID == null && other.eventTypeID != null) || (this.eventTypeID != null && !this.eventTypeID.equals(other.eventTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Eventtype[eventTypeID=" + eventTypeID + "]";
    }

}
