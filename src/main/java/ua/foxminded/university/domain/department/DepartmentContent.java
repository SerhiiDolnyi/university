package ua.foxminded.university.domain.department;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class DepartmentContent {
    private int departmentContentId;
    private int departmentId;    
    private int studentId;
    private int groupId;
    private int teacherId;
    private int timetableId;
    
    public DepartmentContent(final Builder builder) {
        this.departmentContentId = builder.departmentContentId;
        this.departmentId = builder.departmentId;        
        this.studentId = builder.studentId;
        this.groupId = builder.groupId;
        this.teacherId = builder.teacherId;
        this.timetableId = builder.timetableId;
    }
   
    public int getDepartmentContentId() {
        return departmentContentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getTimetableId() {
        return timetableId;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentContentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DepartmentContent other = (DepartmentContent) obj;
        return departmentContentId == other.departmentContentId;
    }
    
    @Override
    public String toString() {
        return "DepartmentContent [departmentContentId=" + departmentContentId + ", departmentId=" + departmentId
                + ", studentId=" + studentId + ", groupId=" + groupId + ", teacherId=" + teacherId + ", timetableId="
                + timetableId + "]";
    }

    public static class Builder {
        private int departmentContentId;
        private int departmentId;    
        private int studentId;
        private int groupId;
        private int teacherId;
        private int timetableId;
        
        private Builder() {            
        }

        public Builder withDepartmentContentId(final int departmentContentId) {
            this.departmentContentId = departmentContentId;
            return this;
        }

        public Builder withDepartmentId(final int departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public Builder withStudentId(final int studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder withGroupId(final int groupId) {
            this.groupId = groupId;
            return this;
        }
       
        public Builder withTeacherId(final int teacherId) {
            this.teacherId = teacherId;
            return this;
        }

        public Builder withTimetableId(final int timetableId) {
            this.timetableId = timetableId;
            return this;
        }
        
        public DepartmentContent build() {
            return new DepartmentContent(this);
        }   
    }
}
