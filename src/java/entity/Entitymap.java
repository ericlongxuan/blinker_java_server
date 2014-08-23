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
@Table(name = "entitymap")
@NamedQueries({
    @NamedQuery(name = "Entitymap.findAll", query = "SELECT e FROM Entitymap e"),
    @NamedQuery(name = "Entitymap.findByEntityID", query = "SELECT e FROM Entitymap e WHERE e.entityID = :entityID"),
    @NamedQuery(name = "Entitymap.findByEntityCoordinate", query = "SELECT e FROM Entitymap e WHERE e.entityCoordinate = :entityCoordinate"),
    @NamedQuery(name = "Entitymap.findByEntitySSID", query = "SELECT e FROM Entitymap e WHERE e.entitySSID = :entitySSID")})
public class Entitymap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EntityID")
    private Integer entityID;
    @Basic(optional = false)
    @Column(name = "EntityCoordinate")
    private double entityCoordinate;
    @Column(name = "EntitySSID")
    private String entitySSID;

    public Entitymap() {
    }

    public Entitymap(Integer entityID) {
        this.entityID = entityID;
    }

    public Entitymap(Integer entityID, double entityCoordinate) {
        this.entityID = entityID;
        this.entityCoordinate = entityCoordinate;
    }

    public Integer getEntityID() {
        return entityID;
    }

    public void setEntityID(Integer entityID) {
        this.entityID = entityID;
    }

    public double getEntityCoordinate() {
        return entityCoordinate;
    }

    public void setEntityCoordinate(double entityCoordinate) {
        this.entityCoordinate = entityCoordinate;
    }

    public String getEntitySSID() {
        return entitySSID;
    }

    public void setEntitySSID(String entitySSID) {
        this.entitySSID = entitySSID;
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
        if (!(object instanceof Entitymap)) {
            return false;
        }
        Entitymap other = (Entitymap) object;
        if ((this.entityID == null && other.entityID != null) || (this.entityID != null && !this.entityID.equals(other.entityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Entitymap[entityID=" + entityID + "]";
    }

}
