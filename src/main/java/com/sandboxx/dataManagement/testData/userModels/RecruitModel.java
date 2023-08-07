package com.sandboxx.dataManagement.testData.userModels;

public class RecruitModel extends Person{



    private String serviceBranch;

    public RecruitModel(String firstName, String lastName, String email, String phoneNumber,String password) {
        super(firstName, lastName, email, phoneNumber,password);
    }

    public String getServiceBranch() {
        return serviceBranch;
    }

    public void setServiceBranch(String serviceBranch) {
        this.serviceBranch = serviceBranch;
    }
}
