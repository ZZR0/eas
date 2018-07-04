package com.example.eas.entities;

/*course_student对象*/
public class CSG {
    Integer cid;
    Integer sid;
    Integer grade;

    @Override
    public String toString() {
        return "CSG{" +
                "cid=" + cid +
                ", sid=" + sid +
                ", grade=" + grade +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public CSG(Integer cid, Integer sid, Integer grade) {

        this.cid = cid;
        this.sid = sid;
        this.grade = grade;
    }

    public CSG() {

    }
}
