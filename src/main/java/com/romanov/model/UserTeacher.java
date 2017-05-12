package com.romanov.model;

/**
 * Created by Kirill_Romanov1 on 29-Mar-17.
 */
public class UserTeacher {
    private String post;
    private String academicDegree;
    private String academicTitle;
    private int idCathedra;
    private int idUser;

    public UserTeacher() {
    }

    public UserTeacher(String post, String academicDegree, int idCathedra, int idUser) {
        this.post = post;
        this.academicDegree = academicDegree;
        this.idCathedra = idCathedra;
        this.idUser = idUser;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public int getIdCathedra() {
        return idCathedra;
    }

    public void setIdCathedra(int idCathedra) {
        this.idCathedra = idCathedra;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
