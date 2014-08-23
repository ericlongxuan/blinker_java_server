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
@Table(name = "relationshipstatus")
@NamedQueries({
    @NamedQuery(name = "Relationshipstatus.findAll", query = "SELECT r FROM Relationshipstatus r"),
    @NamedQuery(name = "Relationshipstatus.findByRelationshipStatusID", query = "SELECT r FROM Relationshipstatus r WHERE r.relationshipStatusID = :relationshipStatusID"),
    @NamedQuery(name = "Relationshipstatus.findByRelationshipStatusName", query = "SELECT r FROM Relationshipstatus r WHERE r.relationshipStatusName = :relationshipStatusName")})
public class Relationshipstatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RelationshipStatusID")
    private Integer relationshipStatusID;
    @Basic(optional = false)
    @Column(name = "RelationshipStatusName")
    private String relationshipStatusName;

    public Relationshipstatus() {
    }

    public Relationshipstatus(Integer relationshipStatusID) {
        this.relationshipStatusID = relationshipStatusID;
    }

    public Relationshipstatus(Integer relationshipStatusID, String relationshipStatusName) {
        this.relationshipStatusID = relationshipStatusID;
        this.relationshipStatusName = relationshipStatusName;
    }

    public Integer getRelationshipStatusID() {
        return relationshipStatusID;
    }

    public void setRelationshipStatusID(Integer relationshipStatusID) {
        this.relationshipStatusID = relationshipStatusID;
    }

    public String getRelationshipStatusName() {
        return relationshipStatusName;
    }

    public void setRelationshipStatusName(String relationshipStatusName) {
        this.relationshipStatusName = relationshipStatusName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relationshipStatusID != null ? relationshipStatusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relationshipstatus)) {
            return false;
        }
        Relationshipstatus other = (Relationshipstatus) object;
        if ((this.relationshipStatusID == null && other.relationshipStatusID != null) || (this.relationshipStatusID != null && !this.relationshipStatusID.equals(other.relationshipStatusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Relationshipstatus[relationshipStatusID=" + relationshipStatusID + "]";
    }

}
