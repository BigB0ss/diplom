package com.romanov.model;

import java.util.*;

/**
 * Created by Kirill on 08.03.2017.
 */
public class TechnicalTask {
    private long id;
    private String name;
    private String target;
    private List<String> tasks;
    private Date dateCreated;
    private Date dateCompleted;
    private int discipline;
    private int typeTechnicalTask;
    private Map<String, List<String>> demands = new HashMap<>();

    public TechnicalTask() {
    }

    public TechnicalTask(long id, String name, String target, Date dateCreated, Date dateCompleted, int typeTechnicalTask, Map<String, List<String>> demands) {
        this.id = id;
        this.name = name;
        this.target = target;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateComplted() {
        return dateCompleted;
    }

    public void setDateComplted(Date dateComplted) {
        this.dateCompleted = dateComplted;
    }

    public int getTypeTechnicalTask() {
        return typeTechnicalTask;
    }

    public void setTypeTechnicalTask(int typeTechnicalTask) {
        this.typeTechnicalTask = typeTechnicalTask;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public Map<String, List<String>> getDemands() {
        return demands;
    }

    public void setDemands(Map<String, List<String>> demands) {
        this.demands = demands;
    }

    public int getDiscipline() {
        return discipline;
    }

    public void setDiscipline(int discipline) {
        this.discipline = discipline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TechnicalTask that = (TechnicalTask) o;

        if (id != that.id) return false;
        if (typeTechnicalTask != that.typeTechnicalTask) return false;
        if (!name.equals(that.name)) return false;
        if (!target.equals(that.target)) return false;
        if (!dateCreated.equals(that.dateCreated)) return false;
        if (!dateCompleted.equals(that.dateCompleted)) return false;

        return demands != null ? demands.equals(that.demands) : that.demands == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + target.hashCode();
        result = 31 * result + dateCreated.hashCode();
        result = 31 * result + dateCompleted.hashCode();
        result = 31 * result + typeTechnicalTask;

        result = 31 * result + (demands != null ? demands.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TechnicalTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", target='" + target + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateCompleted=" + dateCompleted +
                ", typeTechnicalTask=" + typeTechnicalTask +
                ", demands=" + demands +
                ",discipline=" + discipline +
                '}';
    }
}
