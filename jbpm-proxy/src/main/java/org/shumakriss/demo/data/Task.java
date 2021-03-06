package org.shumakriss.demo.data;

import java.util.Date;
import java.util.List;


public class Task {

    private Long id;
    private String name;
    private String subject;
    private String description;
    private String status;
    private Integer priority;
    private Boolean skipable;
    private String actualOwner;
    private String createdBy;
    private Date createdOn;
    private Date activationTime;
    private Date expirationDate;
    private Long processInstanceId;
    private String processId;
    private String containerId;
    private Long parentId;
    private String trackingNumber;
    private List<TaskDatum> inData;
    private List<TaskDatum> outData;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", skipable=" + skipable +
                ", actualOwner='" + actualOwner + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", activationTime=" + activationTime +
                ", expirationDate=" + expirationDate +
                ", processInstanceId=" + processInstanceId +
                ", processId='" + processId + '\'' +
                ", containerId='" + containerId + '\'' +
                ", parentId=" + parentId +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", inData=" + inData +
                ", outData=" + outData +
                '}';
    }


    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public List<TaskDatum> getOutData() {
        return outData;
    }
    public void setOutData(List<TaskDatum> outData) {
        this.outData = outData;
    }

    public List<TaskDatum> getInData() {
        return inData;
    }

    public void setInData(List<TaskDatum> inData) {
        this.inData = inData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getSkipable() {
        return skipable;
    }

    public void setSkipable(Boolean skipable) {
        this.skipable = skipable;
    }

    public String getActualOwner() {
        return actualOwner;
    }

    public void setActualOwner(String actualOwner) {
        this.actualOwner = actualOwner;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(Date activationTime) {
        this.activationTime = activationTime;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(Long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
