package com.wordcount.kafkaconsumerwordcount.restapi_domainchanges;
import java.util.ArrayList;
import java.util.List;
public class DomainChanges {
    private List<DomainChange> domainChangeList;


    public List<DomainChange> getDomainChangeList()
    {

        if (domainChangeList == null) {

            domainChangeList
                    = new ArrayList<>();


        }

        return domainChangeList;


    }

    public void
    setDomainList(
            List<DomainChange> domainChangeList)
    {
        this.domainChangeList
                = domainChangeList;
    }


    public void emptyList(){
        System.out.println("Clearing List");
        domainChangeList.clear();
    }
}
