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
@Table(name = "footprintstatus")
@NamedQueries({
    @NamedQuery(name = "Footprintstatus.findAll", query = "SELECT f FROM Footprintstatus f"),
    @NamedQuery(name = "Footprintstatus.findByFootprintStatusID", query = "SELECT f FROM Footprintstatus f WHERE f.footprintStatusID = :footprintStatusID"),
    @NamedQuery(name = "Footprintstatus.findByFootprintStatus", query = "SELECT f FROM Footprintstatus f WHERE f.footprintStatus = :footprintStatus")})
public class Footprintstatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FootprintStatusID")
    private Integer footprintStatusID;
    @Basic(optional = false)
    @Column(name = "FootprintStatus")
    private String footprintStatus;

    public Footprintstatus() {
    }

    public Footprintstatus(Integer footprintStatusID) {
        this.footprintStatusID = footprintStatusID;
    }

    public Footprintstatus(Integer footprintStatusID, String footprintStatus) {
        this.footprintStatusID = footprintStatusID;
        this.footprintStatus = footprintStatus;
    }

    public Integer getFootprintStatusID() {
        return footprintStatusID;
    }

    public void setFootprintStatusID(Integer footprintStatusID) {
        this.footprintStatusID = footprintStatusID;
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
        hash += (footprintStatusID != null ? footprintStatusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Footprintstatus)) {
            return false;
        }
        Footprintstatus other = (Footprintstatus) object;
        if ((this.footprintStatusID == null && other.footprintStatusID != null) || (this.footprintStatusID != null && !this.footprintStatusID.equals(other.footprintStatusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Footprintstatus[footprintStatusID=" + footprintStatusID + "]";
    }

}
