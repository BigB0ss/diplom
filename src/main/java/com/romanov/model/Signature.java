package com.romanov.model;

/**
 * Created by Kirill_Romanov1 on 24-May-17.
 */
public class Signature {
        private int id;
        private int technicalTaskId;
        private int teacherId;
        private int studentId;

        public Signature() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTechnicalTaskId() {
            return technicalTaskId;
        }

        public void setTechnicalTaskId(int technicalTaskId) {
            this.technicalTaskId = technicalTaskId;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }
    }
