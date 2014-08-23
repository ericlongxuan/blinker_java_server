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
@Table(name = "entityrelationship")
@NamedQueries({
    @NamedQuery(name = "Entityrelationship.findAll", query = "SELECT e FROM Entityrelationship e"),
    @NamedQuery(name = "Entityrelationship.findByEntityID", query = "SELECT e FROM Entityrelationship e WHERE e.entityID = :entityID"),
    @NamedQuery(name = "Entityrelationship.findBySubEntityID", query = "SELECT e FROM Entityrelationship e WHERE e.subEntityID = :subEntityID")})
public class Entityrelationship implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EntityID")
    private Integer entityID;
    @Column(name = "SubEntityID")
    private String subEntityID;

    public Entityrelationship() {
    }

    public Entityrelationship(Integer entityID) {
        this.entityID = entityID;
    }

    public Integer getEntityID() {
        return entityID;
    }

    public void setEntityID(Integer entityID) {
        this.entityID = entityID;
    }

    public String getSubEntityID() {
        return subEntityID;
    }

    public void setSubEntityID(String subEntityID) {
        this.subEntityID = subEntityID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entityID != null ? entityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entityrelationship)) {
            return false;
        }
        Entityrelationship other = (Entityrelationship) object;
        if ((this.entityID == null && other.entityID != null) || (this.entityID != null && !this.entityID.equals(other.entityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Entityrelationship[entityID=" + entityID + "]";
    }

}
