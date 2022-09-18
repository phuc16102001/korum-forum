package vn.elca.training.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPimEntity is a Querydsl query type for PimEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QPimEntity extends EntityPathBase<PimEntity> {

    private static final long serialVersionUID = 1815723186L;

    public static final QPimEntity pimEntity = new QPimEntity("pimEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QPimEntity(String variable) {
        super(PimEntity.class, forVariable(variable));
    }

    public QPimEntity(Path<? extends PimEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPimEntity(PathMetadata metadata) {
        super(PimEntity.class, metadata);
    }

}

