package com.org.tijarah.structure;

/**
 * Created by LENOVO on 1/1/2018.
 */

public class User {

    String name;
    String address;
    String address1;
    String contact;

    public User(String name, String address, String address1, String contact) {
        this.name = name;
        this.address = address;
        this.address1 = address1;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(user.getAddress()) : user.getAddress() != null)
            return false;
        if (getAddress1() != null ? !getAddress1().equals(user.getAddress1()) : user.getAddress1() != null)
            return false;
        return getContact() != null ? getContact().equals(user.getContact()) : user.getContact() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getAddress1() != null ? getAddress1().hashCode() : 0);
        result = 31 * result + (getContact() != null ? getContact().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", address1='" + address1 + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
