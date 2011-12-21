package yanitime4u.yanitime.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2011-11-27 22:45:35")
/** */
public final class UsersMeta extends org.slim3.datastore.ModelMeta<yanitime4u.yanitime.model.Users> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Users> userId = new org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Users>(this, "userId", "userId");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Users> userName = new org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Users>(this, "userName", "userName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Users> password = new org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Users>(this, "password", "password");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Users> email = new org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Users>(this, "email", "email");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Users> nickName = new org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Users>(this, "nickName", "nickName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, java.lang.Integer> age = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, java.lang.Integer>(this, "age", "age", java.lang.Integer.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, yanitime4u.yanitime.model.code.Gender> gender = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, yanitime4u.yanitime.model.code.Gender>(this, "gender", "gender", yanitime4u.yanitime.model.code.Gender.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, com.google.appengine.api.users.User> createdBy = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, com.google.appengine.api.users.User>(this, "createdBy", "createdBy", com.google.appengine.api.users.User.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, java.util.Date> createdAt = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, java.util.Date>(this, "createdAt", "createdAt", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, com.google.appengine.api.users.User> updatedBy = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, com.google.appengine.api.users.User>(this, "updatedBy", "updatedBy", com.google.appengine.api.users.User.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, java.util.Date> updatedAt = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Users, java.util.Date>(this, "updatedAt", "updatedAt", java.util.Date.class);

    private static final org.slim3.datastore.CreationUser slim3_createdByAttributeListener = new org.slim3.datastore.CreationUser();

    private static final org.slim3.datastore.CreationDate slim3_createdAtAttributeListener = new org.slim3.datastore.CreationDate();

    private static final org.slim3.datastore.ModificationUser slim3_updatedByAttributeListener = new org.slim3.datastore.ModificationUser();

    private static final org.slim3.datastore.ModificationDate slim3_updatedAtAttributeListener = new org.slim3.datastore.ModificationDate();

    private static final UsersMeta slim3_singleton = new UsersMeta();

    /**
     * @return the singleton
     */
    public static UsersMeta get() {
       return slim3_singleton;
    }

    /** */
    public UsersMeta() {
        super("Users", yanitime4u.yanitime.model.Users.class);
    }

    @Override
    public yanitime4u.yanitime.model.Users entityToModel(com.google.appengine.api.datastore.Entity entity) {
        yanitime4u.yanitime.model.Users model = new yanitime4u.yanitime.model.Users();
        model.setKey(entity.getKey());
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        model.setUserId((java.lang.String) entity.getProperty("userId"));
        model.setUserName((java.lang.String) entity.getProperty("userName"));
        model.setPassword(decrypt((java.lang.String)entity.getProperty("password")));
        model.setEmail((java.lang.String) entity.getProperty("email"));
        model.setNickName((java.lang.String) entity.getProperty("nickName"));
        model.setAge(longToInteger((java.lang.Long) entity.getProperty("age")));
        model.setGender(stringToEnum(yanitime4u.yanitime.model.code.Gender.class, (java.lang.String) entity.getProperty("gender")));
        model.setCreatedBy((com.google.appengine.api.users.User) entity.getProperty("createdBy"));
        model.setCreatedAt((java.util.Date) entity.getProperty("createdAt"));
        model.setUpdatedBy((com.google.appengine.api.users.User) entity.getProperty("updatedBy"));
        model.setUpdatedAt((java.util.Date) entity.getProperty("updatedAt"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        yanitime4u.yanitime.model.Users m = (yanitime4u.yanitime.model.Users) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("version", m.getVersion());
        entity.setProperty("userId", m.getUserId());
        entity.setProperty("userName", m.getUserName());
        entity.setProperty("password", encrypt(m.getPassword()));
        entity.setProperty("email", m.getEmail());
        entity.setProperty("nickName", m.getNickName());
        entity.setProperty("age", m.getAge());
        entity.setProperty("gender", enumToString(m.getGender()));
        entity.setProperty("createdBy", m.getCreatedBy());
        entity.setProperty("createdAt", m.getCreatedAt());
        entity.setProperty("updatedBy", m.getUpdatedBy());
        entity.setProperty("updatedAt", m.getUpdatedAt());
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        yanitime4u.yanitime.model.Users m = (yanitime4u.yanitime.model.Users) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        yanitime4u.yanitime.model.Users m = (yanitime4u.yanitime.model.Users) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        yanitime4u.yanitime.model.Users m = (yanitime4u.yanitime.model.Users) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        yanitime4u.yanitime.model.Users m = (yanitime4u.yanitime.model.Users) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
        yanitime4u.yanitime.model.Users m = (yanitime4u.yanitime.model.Users) model;
        m.setCreatedBy(slim3_createdByAttributeListener.prePut(m.getCreatedBy()));
        m.setCreatedAt(slim3_createdAtAttributeListener.prePut(m.getCreatedAt()));
        m.setUpdatedBy(slim3_updatedByAttributeListener.prePut(m.getUpdatedBy()));
        m.setUpdatedAt(slim3_updatedAtAttributeListener.prePut(m.getUpdatedAt()));
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        if ("password".equals(propertyName)) return true;
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        yanitime4u.yanitime.model.Users m = (yanitime4u.yanitime.model.Users) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        if(m.getUserId() != null){
            writer.setNextPropertyName("userId");
            encoder0.encode(writer, m.getUserId());
        }
        if(m.getUserName() != null){
            writer.setNextPropertyName("userName");
            encoder0.encode(writer, m.getUserName());
        }
        if(m.getPassword() != null){
            writer.setNextPropertyName("password");
            encoder0.encode(writer, encrypt(m.getPassword()));
        }
        if(m.getEmail() != null){
            writer.setNextPropertyName("email");
            encoder0.encode(writer, m.getEmail());
        }
        if(m.getNickName() != null){
            writer.setNextPropertyName("nickName");
            encoder0.encode(writer, m.getNickName());
        }
        if(m.getAge() != null){
            writer.setNextPropertyName("age");
            encoder0.encode(writer, m.getAge());
        }
        if(m.getGender() != null){
            writer.setNextPropertyName("gender");
            encoder0.encode(writer, m.getGender());
        }
        if(m.getCreatedBy() != null){
            writer.setNextPropertyName("createdBy");
            encoder0.encode(writer, m.getCreatedBy());
        }
        if(m.getCreatedAt() != null){
            writer.setNextPropertyName("createdAt");
            encoder0.encode(writer, m.getCreatedAt());
        }
        if(m.getUpdatedBy() != null){
            writer.setNextPropertyName("updatedBy");
            encoder0.encode(writer, m.getUpdatedBy());
        }
        if(m.getUpdatedAt() != null){
            writer.setNextPropertyName("updatedAt");
            encoder0.encode(writer, m.getUpdatedAt());
        }
        writer.endObject();
    }

    @Override
    protected yanitime4u.yanitime.model.Users jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        yanitime4u.yanitime.model.Users m = new yanitime4u.yanitime.model.Users();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        reader = rootReader.newObjectReader("userId");
        m.setUserId(decoder0.decode(reader, m.getUserId()));
        reader = rootReader.newObjectReader("userName");
        m.setUserName(decoder0.decode(reader, m.getUserName()));
        reader = rootReader.newObjectReader("password");
        if(reader.read() != null){
            reader = new org.slim3.datastore.json.JsonValueReader(decrypt(reader.read()), rootReader.getModelReader());
        }
        m.setPassword(decoder0.decode(reader, m.getPassword()));
        reader = rootReader.newObjectReader("email");
        m.setEmail(decoder0.decode(reader, m.getEmail()));
        reader = rootReader.newObjectReader("nickName");
        m.setNickName(decoder0.decode(reader, m.getNickName()));
        reader = rootReader.newObjectReader("age");
        m.setAge(decoder0.decode(reader, m.getAge()));
        reader = rootReader.newObjectReader("gender");
        m.setGender(decoder0.decode(reader, m.getGender(), yanitime4u.yanitime.model.code.Gender.class));
        reader = rootReader.newObjectReader("createdBy");
        m.setCreatedBy(decoder0.decode(reader, m.getCreatedBy()));
        reader = rootReader.newObjectReader("createdAt");
        m.setCreatedAt(decoder0.decode(reader, m.getCreatedAt()));
        reader = rootReader.newObjectReader("updatedBy");
        m.setUpdatedBy(decoder0.decode(reader, m.getUpdatedBy()));
        reader = rootReader.newObjectReader("updatedAt");
        m.setUpdatedAt(decoder0.decode(reader, m.getUpdatedAt()));
        return m;
    }
}