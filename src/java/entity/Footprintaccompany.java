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
@Table(name = "footprintaccompany")
@NamedQueries({
    @NamedQuery(name = "Footprintaccompany.findAll", query = "SELECT f FROM Footprintaccompany f"),
    @NamedQuery(name = "Footprintaccompany.findByFootprintID", query = "SELECT f FROM Footprintaccompany f WHERE f.footprintID = :footprintID"),
    @NamedQuery(name = "Footprintaccompany.findByFootprintAccompanyID", query = "SELECT f FROM Footprintaccompany f WHERE f.footprintAccompanyID = :footprintAccompanyID")})
public class Footprintaccompany implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FootprintID")
    private Integer footprintID;
    @Column(name = "FootprintAccompanyID")
    private Integer footprintAccompanyID;

    public Footprintaccompany() {
    }

    public Footprintaccompany(Integer footprintID) {
        this.footprintID = footprintID;
    }

    public Integer getFootprintID() {
        return footprintID;
    }

    public void setFootprintID(Integer footprintID) {
        this.footprintID = footprintID;
    }

    public Integer getFootprintAccompanyID() {
        return footprintAccompanyID;
    }

    public void setFootprintAccompanyID(Integer footprintAccompanyID) {
        this.footprintAccompanyID = footprintAccompanyID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (footprintID != null ? footprintID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Footprintaccompany)) {
            return false;
        }
        Footprintaccompany other = (Footprintaccompany) object;
        if ((this.footprintID == null && other.footprintID != null) || (this.footprintID != null && !this.footprintID.equals(other.footprintID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Footprintaccompany[footprintID=" + footprintID + "]";
    }

}
