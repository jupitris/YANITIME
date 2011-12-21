package yanitime4u.yanitime.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2011-11-27 22:45:35")
/** */
public final class PlacesMeta extends org.slim3.datastore.ModelMeta<yanitime4u.yanitime.model.Places> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Places> userId = new org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Places>(this, "userId", "userId");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Places> placeName = new org.slim3.datastore.StringAttributeMeta<yanitime4u.yanitime.model.Places>(this, "placeName", "placeName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, com.google.appengine.api.datastore.GeoPt> coordinate = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, com.google.appengine.api.datastore.GeoPt>(this, "coordinate", "coordinate", com.google.appengine.api.datastore.GeoPt.class);

    /** */
    public final org.slim3.datastore.StringUnindexedAttributeMeta<yanitime4u.yanitime.model.Places> comment = new org.slim3.datastore.StringUnindexedAttributeMeta<yanitime4u.yanitime.model.Places>(this, "comment", "comment");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, com.google.appengine.api.users.User> createdBy = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, com.google.appengine.api.users.User>(this, "createdBy", "createdBy", com.google.appengine.api.users.User.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, java.util.Date> createdAt = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, java.util.Date>(this, "createdAt", "createdAt", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, com.google.appengine.api.users.User> updatedBy = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, com.google.appengine.api.users.User>(this, "updatedBy", "updatedBy", com.google.appengine.api.users.User.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, java.util.Date> updatedAt = new org.slim3.datastore.CoreAttributeMeta<yanitime4u.yanitime.model.Places, java.util.Date>(this, "updatedAt", "updatedAt", java.util.Date.class);

    private static final org.slim3.datastore.CreationUser slim3_createdByAttributeListener = new org.slim3.datastore.CreationUser();

    private static final org.slim3.datastore.CreationDate slim3_createdAtAttributeListener = new org.slim3.datastore.CreationDate();

    private static final org.slim3.datastore.ModificationUser slim3_updatedByAttributeListener = new org.slim3.datastore.ModificationUser();

    private static final org.slim3.datastore.ModificationDate slim3_updatedAtAttributeListener = new org.slim3.datastore.ModificationDate();

    private static final PlacesMeta slim3_singleton = new PlacesMeta();

    /**
     * @return the singleton
     */
    public static PlacesMeta get() {
       return slim3_singleton;
    }

    /** */
    public PlacesMeta() {
        super("Places", yanitime4u.yanitime.model.Places.class);
    }

    @Override
    public yanitime4u.yanitime.model.Places entityToModel(com.google.appengine.api.datastore.Entity entity) {
        yanitime4u.yanitime.model.Places model = new yanitime4u.yanitime.model.Places();
        model.setKey(entity.getKey());
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        model.setUserId((java.lang.String) entity.getProperty("userId"));
        model.setPlaceName((java.lang.String) entity.getProperty("placeName"));
        model.setCoordinate((com.google.appengine.api.datastore.GeoPt) entity.getProperty("coordinate"));
        model.setComment(textToString((com.google.appengine.api.datastore.Text) entity.getProperty("comment")));
        model.setCreatedBy((com.google.appengine.api.users.User) entity.getProperty("createdBy"));
        model.setCreatedAt((java.util.Date) entity.getProperty("createdAt"));
        model.setUpdatedBy((com.google.appengine.api.users.User) entity.getProperty("updatedBy"));
        model.setUpdatedAt((java.util.Date) entity.getProperty("updatedAt"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        yanitime4u.yanitime.model.Places m = (yanitime4u.yanitime.model.Places) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("version", m.getVersion());
        entity.setProperty("userId", m.getUserId());
        entity.setProperty("placeName", m.getPlaceName());
        entity.setProperty("coordinate", m.getCoordinate());
        entity.setUnindexedProperty("comment", stringToText(m.getComment()));
        entity.setProperty("createdBy", m.getCreatedBy());
        entity.setProperty("createdAt", m.getCreatedAt());
        entity.setProperty("updatedBy", m.getUpdatedBy());
        entity.setProperty("updatedAt", m.getUpdatedAt());
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        yanitime4u.yanitime.model.Places m = (yanitime4u.yanitime.model.Places) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        yanitime4u.yanitime.model.Places m = (yanitime4u.yanitime.model.Places) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        yanitime4u.yanitime.model.Places m = (yanitime4u.yanitime.model.Places) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        yanitime4u.yanitime.model.Places m = (yanitime4u.yanitime.model.Places) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
        yanitime4u.yanitime.model.Places m = (yanitime4u.yanitime.model.Places) model;
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
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        yanitime4u.yanitime.model.Places m = (yanitime4u.yanitime.model.Places) model;
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
        if(m.getPlaceName() != null){
            writer.setNextPropertyName("placeName");
            encoder0.encode(writer, m.getPlaceName());
        }
        if(m.getCoordinate() != null){
            writer.setNextPropertyName("coordinate");
            encoder0.encode(writer, m.getCoordinate());
        }
        if(m.getComment() != null){
            writer.setNextPropertyName("comment");
            encoder0.encode(writer, m.getComment());
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
    protected yanitime4u.yanitime.model.Places jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        yanitime4u.yanitime.model.Places m = new yanitime4u.yanitime.model.Places();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        reader = rootReader.newObjectReader("userId");
        m.setUserId(decoder0.decode(reader, m.getUserId()));
        reader = rootReader.newObjectReader("placeName");
        m.setPlaceName(decoder0.decode(reader, m.getPlaceName()));
        reader = rootReader.newObjectReader("coordinate");
        m.setCoordinate(decoder0.decode(reader, m.getCoordinate()));
        reader = rootReader.newObjectReader("comment");
        m.setComment(decoder0.decode(reader, m.getComment()));
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