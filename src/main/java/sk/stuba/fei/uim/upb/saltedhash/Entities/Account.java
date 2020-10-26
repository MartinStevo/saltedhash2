package sk.stuba.fei.uim.upb.saltedhash.Entities;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
public class Account {

    @Id
    private String username;
    @Type(type = "org.hibernate.type.BlobType")
    @Lob
    private byte[] salt;
    @Type(type = "org.hibernate.type.BlobType")
    @Lob
    private byte[] saltedHash;

    public Account(String username, byte[] salt, byte[] saltedHash) {
        this.username = username;
        this.salt = salt;
        this.saltedHash = saltedHash;
    }

    public Account(String username, byte[] salt) {

    }

    public Account() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSaltedHash() {
        return saltedHash;
    }

    public void setSaltedHash(byte[] saltedHash) {
        this.saltedHash = saltedHash;
    }
}
