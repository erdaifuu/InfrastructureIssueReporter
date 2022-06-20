package com.example.infrastructureissuereporter;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.*;

import java.util.List;
import java.util.Date;

public class SectionIssues
{
    private String Description;
    private Integer Section;
    private Date updated;
    private String InputDate;
    private Integer YPosition;
    private String ownerId;
    private String Issue;
    private String objectId;
    private Date created;
    private Integer XPosition;
    public String getDescription()
    {
        return Description;
    }

    public void setDescription( String Description )
    {
        this.Description = Description;
    }

    public Integer getSection()
    {
        return Section;
    }

    public void setSection( Integer Section )
    {
        this.Section = Section;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public String getInputDate()
    {
        return InputDate;
    }

    public void setInputDate( String InputDate )
    {
        this.InputDate = InputDate;
    }

    public Integer getYPosition()
    {
        return YPosition;
    }

    public void setYPosition( Integer YPosition )
    {
        this.YPosition = YPosition;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public String getIssue()
    {
        return Issue;
    }

    public void setIssue( String Issue )
    {
        this.Issue = Issue;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public Date getCreated()
    {
        return created;
    }

    public Integer getXPosition()
    {
        return XPosition;
    }

    public void setXPosition( Integer XPosition )
    {
        this.XPosition = XPosition;
    }


    public SectionIssues save()
    {
        return Backendless.Data.of( SectionIssues.class ).save( this );
    }

    public void saveAsync( AsyncCallback<SectionIssues> callback )
    {
        Backendless.Data.of( SectionIssues.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( SectionIssues.class ).remove( this );
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( SectionIssues.class ).remove( this, callback );
    }

    public static SectionIssues findById(String id )
    {
        return Backendless.Data.of( SectionIssues.class ).findById( id );
    }

    public static void findByIdAsync( String id, AsyncCallback<SectionIssues> callback )
    {
        Backendless.Data.of( SectionIssues.class ).findById( id, callback );
    }

    public static SectionIssues findFirst()
    {
        return Backendless.Data.of( SectionIssues.class ).findFirst();
    }

    public static void findFirstAsync( AsyncCallback<SectionIssues> callback )
    {
        Backendless.Data.of( SectionIssues.class ).findFirst( callback );
    }

    public static SectionIssues findLast()
    {
        return Backendless.Data.of( SectionIssues.class ).findLast();
    }

    public static void findLastAsync( AsyncCallback<SectionIssues> callback )
    {
        Backendless.Data.of( SectionIssues.class ).findLast( callback );
    }

    public static List<SectionIssues> find(DataQueryBuilder queryBuilder )
    {
        return Backendless.Data.of( SectionIssues.class ).find( queryBuilder );
    }

    public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<SectionIssues>> callback )
    {
        Backendless.Data.of( SectionIssues.class ).find( queryBuilder, callback );
    }
}