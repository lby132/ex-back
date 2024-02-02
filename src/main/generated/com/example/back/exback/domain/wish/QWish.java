package com.example.back.exback.domain.wish;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWish is a Querydsl query type for Wish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWish extends EntityPathBase<Wish> {

    private static final long serialVersionUID = -1617345886L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWish wish = new QWish("wish");

    public final com.example.back.exback.domain.QBaseEntity _super = new com.example.back.exback.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDateTime = _super.createDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.back.exback.domain.item.QItem item;

    public final com.example.back.exback.domain.member.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDateTime = _super.modifiedDateTime;

    public QWish(String variable) {
        this(Wish.class, forVariable(variable), INITS);
    }

    public QWish(Path<? extends Wish> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWish(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWish(PathMetadata metadata, PathInits inits) {
        this(Wish.class, metadata, inits);
    }

    public QWish(Class<? extends Wish> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new com.example.back.exback.domain.item.QItem(forProperty("item")) : null;
        this.member = inits.isInitialized("member") ? new com.example.back.exback.domain.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

