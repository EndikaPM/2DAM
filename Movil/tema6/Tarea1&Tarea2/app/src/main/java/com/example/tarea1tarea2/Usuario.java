package com.example.tarea1tarea2;

public class Usuario {
    private String user;
    private String password;
    private String email;
    public Usuario(String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean comprovarAcces (String userLog, String pass){
        if (this.user.equals(userLog) && this.password.equals(pass)){
            return true;
        }
        return false;
    }
}
