package com.wordcount.kafkaconsumerwordcount.restapi_domainchanges;

public class DomainChange {
    public DomainChange() {}


    public DomainChange(
            Integer count, String domainName)
    {


        super();

        System.out.println("added "+domainName);

        this.count = count;

        this.domainName = domainName;



    }

    private Integer count;

    private String domainName;




    @Override
    public String toString()
    {

        return "Employee [count="
                + count + ", domainName="
                + domainName + "]";


    }



    public Integer getCount() {
        return count;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
