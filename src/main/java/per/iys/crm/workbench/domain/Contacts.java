package per.iys.crm.workbench.domain;

public class Contacts {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.id
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.owner
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String owner;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.source
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String source;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.customer_id
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String customerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.full_name
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String fullName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.appellation
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String appellation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.email
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.m_phone
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String mPhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.job
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String job;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.create_by
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.create_time
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.edit_by
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String editBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.edit_time
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String editTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.description
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.contact_summary
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String contactSummary;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.next_contact_time
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String nextContactTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts.address
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    private String address;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.id
     *
     * @return the value of tbl_contacts.id
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.id
     *
     * @param id the value for tbl_contacts.id
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.owner
     *
     * @return the value of tbl_contacts.owner
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.owner
     *
     * @param owner the value for tbl_contacts.owner
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.source
     *
     * @return the value of tbl_contacts.source
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.source
     *
     * @param source the value for tbl_contacts.source
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.customer_id
     *
     * @return the value of tbl_contacts.customer_id
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.customer_id
     *
     * @param customerId the value for tbl_contacts.customer_id
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.full_name
     *
     * @return the value of tbl_contacts.full_name
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.full_name
     *
     * @param fullName the value for tbl_contacts.full_name
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.appellation
     *
     * @return the value of tbl_contacts.appellation
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getAppellation() {
        return appellation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.appellation
     *
     * @param appellation the value for tbl_contacts.appellation
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setAppellation(String appellation) {
        this.appellation = appellation == null ? null : appellation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.email
     *
     * @return the value of tbl_contacts.email
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.email
     *
     * @param email the value for tbl_contacts.email
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.m_phone
     *
     * @return the value of tbl_contacts.m_phone
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getmPhone() {
        return mPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.m_phone
     *
     * @param mPhone the value for tbl_contacts.m_phone
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setmPhone(String mPhone) {
        this.mPhone = mPhone == null ? null : mPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.job
     *
     * @return the value of tbl_contacts.job
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getJob() {
        return job;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.job
     *
     * @param job the value for tbl_contacts.job
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.create_by
     *
     * @return the value of tbl_contacts.create_by
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.create_by
     *
     * @param createBy the value for tbl_contacts.create_by
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.create_time
     *
     * @return the value of tbl_contacts.create_time
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.create_time
     *
     * @param createTime the value for tbl_contacts.create_time
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.edit_by
     *
     * @return the value of tbl_contacts.edit_by
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getEditBy() {
        return editBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.edit_by
     *
     * @param editBy the value for tbl_contacts.edit_by
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setEditBy(String editBy) {
        this.editBy = editBy == null ? null : editBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.edit_time
     *
     * @return the value of tbl_contacts.edit_time
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getEditTime() {
        return editTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.edit_time
     *
     * @param editTime the value for tbl_contacts.edit_time
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setEditTime(String editTime) {
        this.editTime = editTime == null ? null : editTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.description
     *
     * @return the value of tbl_contacts.description
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.description
     *
     * @param description the value for tbl_contacts.description
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.contact_summary
     *
     * @return the value of tbl_contacts.contact_summary
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getContactSummary() {
        return contactSummary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.contact_summary
     *
     * @param contactSummary the value for tbl_contacts.contact_summary
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setContactSummary(String contactSummary) {
        this.contactSummary = contactSummary == null ? null : contactSummary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.next_contact_time
     *
     * @return the value of tbl_contacts.next_contact_time
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getNextContactTime() {
        return nextContactTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.next_contact_time
     *
     * @param nextContactTime the value for tbl_contacts.next_contact_time
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime == null ? null : nextContactTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts.address
     *
     * @return the value of tbl_contacts.address
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts.address
     *
     * @param address the value for tbl_contacts.address
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}