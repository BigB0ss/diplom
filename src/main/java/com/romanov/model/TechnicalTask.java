package com.romanov.model;

import java.util.*;

/**
 * Created by Kirill on 08.03.2017.
 */
public class TechnicalTask {
    private long id;
    private String name;
    private String target;
    private Date dateCreated;
    private Date dateComplted;
    private int discipline;
    private int typeTechnicalTask;
    private List<Integer> hardware = new ArrayList<>();
    private List<Integer> software = new ArrayList<>();
    private Map<String, String> demands = new HashMap<>();

    public TechnicalTask() {
    }

    public TechnicalTask(long id, String name, String target, Date dateCreated, Date dateComplted, int typeTechnicalTask, List<Integer> hardware, List<Integer> software, Map<String, String> demands) {
        this.id = id;
        this.name = name;
        this.target = target;
        this.dateCreated = dateCreated;
        this.dateComplted = dateComplted;
        this.typeTechnicalTask = typeTechnicalTask;
        this.hardware = hardware;
        this.software = software;
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
        return dateComplted;
    }

    public void setDateComplted(Date dateComplted) {
        this.dateComplted = dateComplted;
    }

    public int getTypeTechnicalTask() {
        return typeTechnicalTask;
    }

    public void setTypeTechnicalTask(int typeTechnicalTask) {
        this.typeTechnicalTask = typeTechnicalTask;
    }

    public List<Integer> getHardware() {
        return hardware;
    }

    public void setHardware(List<Integer> hardware) {
        this.hardware = hardware;
    }

    public List<Integer> getSoftware() {
        return software;
    }

    public void setSoftware(List<Integer> software) {
        this.software = software;
    }

    public Map<String, String> getDemands() {
        return demands;
    }

    public void setDemands(Map<String, String> demands) {
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
        if (!dateComplted.equals(that.dateComplted)) return false;
        if (hardware != null ? !hardware.equals(that.hardware) : that.hardware != null) return false;
        if (software != null ? !software.equals(that.software) : that.software != null) return false;
        return demands != null ? demands.equals(that.demands) : that.demands == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + target.hashCode();
        result = 31 * result + dateCreated.hashCode();
        result = 31 * result + dateComplted.hashCode();
        result = 31 * result + typeTechnicalTask;
        result = 31 * result + (hardware != null ? hardware.hashCode() : 0);
        result = 31 * result + (software != null ? software.hashCode() : 0);
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
                ", dateComplted=" + dateComplted +
                ", typeTechnicalTask=" + typeTechnicalTask +
                ", hardware=" + hardware +
                ", software=" + software +
                ", demands=" + demands +
                ",discipline=" + discipline +
                '}';
    }
}
