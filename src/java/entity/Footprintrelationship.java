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
@Table(name = "footprintrelationship")
@NamedQueries({
    @NamedQuery(name = "Footprintrelationship.findAll", query = "SELECT f FROM Footprintrelationship f"),
    @NamedQuery(name = "Footprintrelationship.findByUserID", query = "SELECT f FROM Footprintrelationship f WHERE f.userID = :userID"),
    @NamedQuery(name = "Footprintrelationship.findByFootprintID", query = "SELECT f FROM Footprintrelationship f WHERE f.footprintID = :footprintID"),
    @NamedQuery(name = "Footprintrelationship.findByEntityID", query = "SELECT f FROM Footprintrelationship f WHERE f.entityID = :entityID"),
    @NamedQuery(name = "Footprintrelationship.findByFootprintStatus", query = "SELECT f FROM Footprintrelationship f WHERE f.footprintStatus = :footprintStatus")})
public class Footprintrelationship implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserID")
    private Integer userID;
    @Column(name = "FootprintID")
    private Integer footprintID;
    @Column(name = "EntityID")
    private Integer entityID;
    @Column(name = "FootprintStatus")
    private String footprintStatus;

    public Footprintrelationship() {
    }

    public Footprintrelationship(Integer userID) {
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getFootprintID() {
        return footprintID;
    }

    public void setFootprintID(Integer footprintID) {
        this.footprintID = footprintID;
    }

    public Integer getEntityID() {
        return entityID;
    }

    public void setEntityID(Integer entityID) {
        this.entityID = entityID;
    }

    public String getFootprintStatus() {
        return footprintStatus;
    }

    public void setFootprintStatus(String footprintStatus) {
        this.footprintStatus = footprintStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Footprintrelationship)) {
            return false;
        }
        Footprintrelationship other = (Footprintrelationship) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Footprintrelationship[userID=" + userID + "]";
    }

}
