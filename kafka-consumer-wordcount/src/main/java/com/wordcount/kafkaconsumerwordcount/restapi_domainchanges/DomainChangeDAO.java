package com.wordcount.kafkaconsumerwordcount.restapi_domainchanges;
import org.springframework
        .stereotype
        .Repository;

@Repository
public class DomainChangeDAO {
    private static DomainChanges list
            = new DomainChanges();


    static  // testing data
    {


        list.getDomainChangeList().add(
                new DomainChange(
                        1,
                        "en@gmail.com"));

        list.getDomainChangeList().add(
                new DomainChange(
                        11,
                        "wiki@gmail.com"));

        list.getDomainChangeList().add(
                new DomainChange(
                        2,
                        "hello@gmail.com"));


    }


    public DomainChanges getAllDomainChanges()
    {

        return list;
    }

    public void
    addDomain(DomainChange domainChange)
    {
        list.getDomainChangeList()
                .add(domainChange);

    }

    public void removeAllChanges(){
        list.emptyList();
    }
}
