package cern.be.ics.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMyEntity is a Querydsl query type for MyEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMyEntity extends EntityPathBase<MyEntity> {

    private static final long serialVersionUID = -1528135478L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMyEntity myEntity = new QMyEntity("myEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QNestedEntity nestedEntity;

    public QMyEntity(String variable) {
        this(MyEntity.class, forVariable(variable), INITS);
    }

    public QMyEntity(Path<? extends MyEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMyEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMyEntity(PathMetadata<?> metadata, PathInits inits) {
        this(MyEntity.class, metadata, inits);
    }

    public QMyEntity(Class<? extends MyEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nestedEntity = inits.isInitialized("nestedEntity") ? new QNestedEntity(forProperty("nestedEntity")) : null;
    }

}

