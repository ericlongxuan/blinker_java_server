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
@Table(name = "entityprofile")
@NamedQueries({
    @NamedQuery(name = "Entityprofile.findAll", query = "SELECT e FROM Entityprofile e"),
    @NamedQuery(name = "Entityprofile.findByEntityID", query = "SELECT e FROM Entityprofile e WHERE e.entityID = :entityID"),
    @NamedQuery(name = "Entityprofile.findByEntityName", query = "SELECT e FROM Entityprofile e WHERE e.entityName = :entityName"),
    @NamedQuery(name = "Entityprofile.findByEntityLocation", query = "SELECT e FROM Entityprofile e WHERE e.entityLocation = :entityLocation"),
    @NamedQuery(name = "Entityprofile.findByEntityPictureSmallURL", query = "SELECT e FROM Entityprofile e WHERE e.entityPictureSmallURL = :entityPictureSmallURL"),
    @NamedQuery(name = "Entityprofile.findByEntityPictureURL", query = "SELECT e FROM Entityprofile e WHERE e.entityPictureURL = :entityPictureURL"),
    @NamedQuery(name = "Entityprofile.findByEntityPictureBigURL", query = "SELECT e FROM Entityprofile e WHERE e.entityPictureBigURL = :entityPictureBigURL"),
    @NamedQuery(name = "Entityprofile.findByEntityDescription", query = "SELECT e FROM Entityprofile e WHERE e.entityDescription = :entityDescription"),
    @NamedQuery(name = "Entityprofile.findByEntityOwner", query = "SELECT e FROM Entityprofile e WHERE e.entityOwner = :entityOwner"),
    @NamedQuery(name = "Entityprofile.findByEntityRooter", query = "SELECT e FROM Entityprofile e WHERE e.entityRooter = :entityRooter"),
    @NamedQuery(name = "Entityprofile.findByEntityType", query = "SELECT e FROM Entityprofile e WHERE e.entityType = :entityType"),
    @NamedQuery(name = "Entityprofile.findByEntityStatus", query = "SELECT e FROM Entityprofile e WHERE e.entityStatus = :entityStatus"),
    @NamedQuery(name = "Entityprofile.findByEntityFootprinterBeenHere", query = "SELECT e FROM Entityprofile e WHERE e.entityFootprinterBeenHere = :entityFootprinterBeenHere"),
    @NamedQuery(name = "Entityprofile.findByEntityFootprinterBeingHere", query = "SELECT e FROM Entityprofile e WHERE e.entityFootprinterBeingHere = :entityFootprinterBeingHere"),
    @NamedQuery(name = "Entityprofile.findByIsSubEntityExist", query = "SELECT e FROM Entityprofile e WHERE e.isSubEntityExist = :isSubEntityExist")})
public class Entityprofile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EntityID")
    private Integer entityID;
    @Basic(optional = false)
    @Column(name = "EntityName")
    private String entityName;
    @Column(name = "EntityLocation")
    private String entityLocation;
    @Column(name = "EntityPictureSmallURL")
    private String entityPictureSmallURL;
    @Column(name = "EntityPictureURL")
    private String entityPictureURL;
    @Column(name = "EntityPictureBigURL")
    private String entityPictureBigURL;
    @Column(name = "EntityDescription")
    private String entityDescription;
    @Column(name = "EntityOwner")
    private Integer entityOwner;
    @Basic(optional = false)
    @Column(name = "EntityRooter")
    private int entityRooter;
    @Basic(optional = false)
    @Column(name = "EntityType")
    private String entityType;
    @Basic(optional = false)
    @Column(name = "EntityStatus")
    private boolean entityStatus;
    @Basic(optional = false)
    @Column(name = "EntityFootprinterBeenHere")
    private int entityFootprinterBeenHere;
    @Basic(optional = false)
    @Column(name = "EntityFootprinterBeingHere")
    private int entityFootprinterBeingHere;
    @Column(name = "IsSubEntityExist")
    private Boolean isSubEntityExist;

    public Entityprofile() {
    }

    public Entityprofile(Integer entityID) {
        this.entityID = entityID;
    }

    public Entityprofile(Integer entityID, String entityName, int entityRooter, String entityType, boolean entityStatus, int entityFootprinterBeenHere, int entityFootprinterBeingHere) {
        this.entityID = entityID;
        this.entityName = entityName;
        this.entityRooter = entityRooter;
        this.entityType = entityType;
        this.entityStatus = entityStatus;
        this.entityFootprinterBeenHere = entityFootprinterBeenHere;
        this.entityFootprinterBeingHere = entityFootprinterBeingHere;
    }

    public Integer getEntityID() {
        return entityID;
    }

    public void setEntityID(Integer entityID) {
        this.entityID = entityID;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityLocation() {
        return entityLocation;
    }

    public void setEntityLocation(String entityLocation) {
        this.entityLocation = entityLocation;
    }

    public String getEntityPictureSmallURL() {
        return entityPictureSmallURL;
    }

    public void setEntityPictureSmallURL(String entityPictureSmallURL) {
        this.entityPictureSmallURL = entityPictureSmallURL;
    }

    public String getEntityPictureURL() {
        return entityPictureURL;
    }

    public void setEntityPictureURL(String entityPictureURL) {
        this.entityPictureURL = entityPictureURL;
    }

    public String getEntityPictureBigURL() {
        return entityPictureBigURL;
    }

    public void setEntityPictureBigURL(String entityPictureBigURL) {
        this.entityPictureBigURL = entityPictureBigURL;
    }

    public String getEntityDescription() {
        return entityDescription;
    }

    public void setEntityDescription(String entityDescription) {
        this.entityDescription = entityDescription;
    }

    public Integer getEntityOwner() {
        return entityOwner;
    }

    public void setEntityOwner(Integer entityOwner) {
        this.entityOwner = entityOwner;
    }

    public int getEntityRooter() {
        return entityRooter;
    }

    public void setEntityRooter(int entityRooter) {
        this.entityRooter = entityRooter;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public boolean getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(boolean entityStatus) {
        this.entityStatus = entityStatus;
    }

    public int getEntityFootprinterBeenHere() {
        return entityFootprinterBeenHere;
    }

    public void setEntityFootprinterBeenHere(int entityFootprinterBeenHere) {
        this.entityFootprinterBeenHere = entityFootprinterBeenHere;
    }

    public int getEntityFootprinterBeingHere() {
        return entityFootprinterBeingHere;
    }

    public void setEntityFootprinterBeingHere(int entityFootprinterBeingHere) {
        this.entityFootprinterBeingHere = entityFootprinterBeingHere;
    }

    public Boolean getIsSubEntityExist() {
        return isSubEntityExist;
    }

    public void setIsSubEntityExist(Boolean isSubEntityExist) {
        this.isSubEntityExist = isSubEntityExist;
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
        if (!(object instanceof Entityprofile)) {
            return false;
        }
        Entityprofile other = (Entityprofile) object;
        if ((this.entityID == null && other.entityID != null) || (this.entityID != null && !this.entityID.equals(other.entityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Entityprofile[entityID=" + entityID + "]";
    }

}
