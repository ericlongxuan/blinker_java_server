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
@Table(name = "entitytype")
@NamedQueries({
    @NamedQuery(name = "Entitytype.findAll", query = "SELECT e FROM Entitytype e"),
    @NamedQuery(name = "Entitytype.findByEntityTypeID", query = "SELECT e FROM Entitytype e WHERE e.entityTypeID = :entityTypeID"),
    @NamedQuery(name = "Entitytype.findByEntityTypeName", query = "SELECT e FROM Entitytype e WHERE e.entityTypeName = :entityTypeName")})
public class Entitytype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EntityTypeID")
    private Integer entityTypeID;
    @Basic(optional = false)
    @Column(name = "EntityTypeName")
    private String entityTypeName;

    public Entitytype() {
    }

    public Entitytype(Integer entityTypeID) {
        this.entityTypeID = entityTypeID;
    }

    public Entitytype(Integer entityTypeID, String entityTypeName) {
        this.entityTypeID = entityTypeID;
        this.entityTypeName = entityTypeName;
    }

    public Integer getEntityTypeID() {
        return entityTypeID;
    }

    public void setEntityTypeID(Integer entityTypeID) {
        this.entityTypeID = entityTypeID;
    }

    public String getEntityTypeName() {
        return entityTypeName;
    }

    public void setEntityTypeName(String entityTypeName) {
        this.entityTypeName = entityTypeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entityTypeID != null ? entityTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entitytype)) {
            return false;
        }
        Entitytype other = (Entitytype) object;
        if ((this.entityTypeID == null && other.entityTypeID != null) || (this.entityTypeID != null && !this.entityTypeID.equals(other.entityTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Entitytype[entityTypeID=" + entityTypeID + "]";
    }

}
