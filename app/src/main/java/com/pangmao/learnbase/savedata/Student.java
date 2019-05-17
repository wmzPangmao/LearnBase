package com.pangmao.learnbase.savedata;

import androidx.annotation.NonNull;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class Student extends LitePalSupport {
    private long id;
    private String name;
    private String schoolNo;

    public Student(String name, String schoolNo) {
        this.name = name;
        this.schoolNo = schoolNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(String schoolNo) {
        this.schoolNo = schoolNo;
    }

    /**
     * 解决重复保存的问题
     * @return
     */
    @Override
    public boolean save() {
        Student sd = findPersonByPersonId(schoolNo);
        //sqlite中自增id从1开始，0表示不存在
        if (sd == null || sd.id == 0) {
            return super.save();
        } else {
            return false;
        }
    }

    private Student findPersonByPersonId(String schoolNo) {
        List<Student> persons = LitePal.where("schoolNo = ?", schoolNo).find(Student.class);
        if (persons == null || persons.size() == 0) {
            return null;
        } else {
            for (int i = 1; i < persons.size(); i++) {
                persons.get(i).delete();
            }
        }
        return persons.get(0);
    }

    @NonNull
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", schoolNo='" + schoolNo + '\'' +
                '}';
    }
}
