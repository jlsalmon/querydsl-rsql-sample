package cern.be.ics.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QNestedEntity is a Querydsl query type for NestedEntity
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QNestedEntity extends BeanPath<NestedEntity> {

    private static final long serialVersionUID = -1944032683L;

    public static final QNestedEntity nestedEntity = new QNestedEntity("nestedEntity");

    public final StringPath description = createString("description");

    public QNestedEntity(String variable) {
        super(NestedEntity.class, forVariable(variable));
    }

    public QNestedEntity(Path<? extends NestedEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNestedEntity(PathMetadata<?> metadata) {
        super(NestedEntity.class, metadata);
    }

}

