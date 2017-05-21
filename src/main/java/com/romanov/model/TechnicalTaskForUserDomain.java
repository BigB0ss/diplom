package com.romanov.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill on 21.05.2017.
 */
public class TechnicalTaskForUserDomain {
    private long id;
    private String name;
    private String target;
    private List<String> tasks;
    private Date dateCreated;
    private Date dateCompleted;
    private String discipline;
    private String typeTechnicalTask;
    private Map<String, List<String>> demands = new HashMap<>();

    public TechnicalTaskForUserDomain() {
    }

    public TechnicalTaskForUserDomain(long id, String name, String target, List<String> tasks, Date dateCreated, Date dateCompleted, String discipline, String typeTechnicalTask, Map<String, List<String>> demands) {
        this.id = id;
        this.name = name;
        this.target = target;
        this.tasks = tasks;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
        this.discipline = discipline;
        this.typeTechnicalTask = typeTechnicalTask;
        this.demands = demands;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getTypeTechnicalTask() {
        return typeTechnicalTask;
    }

    public void setTypeTechnicalTask(String typeTechnicalTask) {
        this.typeTechnicalTask = typeTechnicalTask;
    }

    public Map<String, List<String>> getDemands() {
        return demands;
    }

    public void setDemands(Map<String, List<String>> demands) {
        this.demands = demands;
    }

    @Override
    public String toString() {
        return "TechnicalTaskForUserDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", target='" + target + '\'' +
                ", tasks=" + tasks +
                ", dateCreated=" + dateCreated +
                ", dateCompleted=" + dateCompleted +
                ", discipline='" + discipline + '\'' +
                ", typeTechnicalTask='" + typeTechnicalTask + '\'' +
                ", demands=" + demands +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechnicalTaskForUserDomain)) return false;

        TechnicalTaskForUserDomain that = (TechnicalTaskForUserDomain) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (target != null ? !target.equals(that.target) : that.target != null) return false;
        if (tasks != null ? !tasks.equals(that.tasks) : that.tasks != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (dateCompleted != null ? !dateCompleted.equals(that.dateCompleted) : that.dateCompleted != null)
            return false;
        if (discipline != null ? !discipline.equals(that.discipline) : that.discipline != null) return false;
        if (typeTechnicalTask != null ? !typeTechnicalTask.equals(that.typeTechnicalTask) : that.typeTechnicalTask != null)
            return false;
        return demands != null ? demands.equals(that.demands) : that.demands == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateCompleted != null ? dateCompleted.hashCode() : 0);
        result = 31 * result + (discipline != null ? discipline.hashCode() : 0);
        result = 31 * result + (typeTechnicalTask != null ? typeTechnicalTask.hashCode() : 0);
        result = 31 * result + (demands != null ? demands.hashCode() : 0);
        return result;
    }
}

