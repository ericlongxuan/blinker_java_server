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
@Table(name = "usercardprofile")
@NamedQueries({
    @NamedQuery(name = "Usercardprofile.findAll", query = "SELECT u FROM Usercardprofile u"),
    @NamedQuery(name = "Usercardprofile.findByUserID", query = "SELECT u FROM Usercardprofile u WHERE u.userID = :userID"),
    @NamedQuery(name = "Usercardprofile.findByBirthday", query = "SELECT u FROM Usercardprofile u WHERE u.birthday = :birthday"),
    @NamedQuery(name = "Usercardprofile.findByNickName", query = "SELECT u FROM Usercardprofile u WHERE u.nickName = :nickName"),
    @NamedQuery(name = "Usercardprofile.findByGender", query = "SELECT u FROM Usercardprofile u WHERE u.gender = :gender"),
    @NamedQuery(name = "Usercardprofile.findByLookingFor", query = "SELECT u FROM Usercardprofile u WHERE u.lookingFor = :lookingFor"),
    @NamedQuery(name = "Usercardprofile.findByPictureSmallURL", query = "SELECT u FROM Usercardprofile u WHERE u.pictureSmallURL = :pictureSmallURL"),
    @NamedQuery(name = "Usercardprofile.findByPictureURL", query = "SELECT u FROM Usercardprofile u WHERE u.pictureURL = :pictureURL"),
    @NamedQuery(name = "Usercardprofile.findByPictureBigURL", query = "SELECT u FROM Usercardprofile u WHERE u.pictureBigURL = :pictureBigURL"),
    @NamedQuery(name = "Usercardprofile.findByRelationshipStatus", query = "SELECT u FROM Usercardprofile u WHERE u.relationshipStatus = :relationshipStatus"),
    @NamedQuery(name = "Usercardprofile.findByUpdateTime", query = "SELECT u FROM Usercardprofile u WHERE u.updateTime = :updateTime"),
    @NamedQuery(name = "Usercardprofile.findByMedal", query = "SELECT u FROM Usercardprofile u WHERE u.medal = :medal"),
    @NamedQuery(name = "Usercardprofile.findBySignature", query = "SELECT u FROM Usercardprofile u WHERE u.signature = :signature")})
public class Usercardprofile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserID")
    private Integer userID;
    @Column(name = "Birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Column(name = "NickName")
    private String nickName;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "LookingFor")
    private String lookingFor;
    @Column(name = "PictureSmallURL")
    private String pictureSmallURL;
    @Column(name = "PictureURL")
    private String pictureURL;
    @Column(name = "PictureBigURL")
    private String pictureBigURL;
    @Column(name = "RelationshipStatus")
    private String relationshipStatus;
    @Column(name = "UpdateTime")
    private String updateTime;
    @Column(name = "Medal")
    private String medal;
    @Column(name = "Signature")
    private String signature;

    public Usercardprofile() {
    }

    public Usercardprofile(Integer userID) {
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLookingFor() {
        return lookingFor;
    }

    public void setLookingFor(String lookingFor) {
        this.lookingFor = lookingFor;
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

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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
        if (!(object instanceof Usercardprofile)) {
            return false;
        }
        Usercardprofile other = (Usercardprofile) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usercardprofile[userID=" + userID + "]";
    }

}
