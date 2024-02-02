package com.example.back.exback.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1934754942L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.example.back.exback.domain.QBaseEntity _super = new com.example.back.exback.domain.QBaseEntity(this);

    public final com.example.back.exback.domain.address.QAddress address;

    public final ComparablePath<Character> banned = createComparable("banned", Character.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDateTime = _super.createDateTime;

    public final ComparablePath<Character> gender = createComparable("gender", Character.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDateTime = _super.modifiedDateTime;

    public final StringPath phone = createString("phone");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final ComparablePath<Character> singOut = createComparable("singOut", Character.class);

    public final StringPath userId = createString("userId");

    public final StringPath userPw = createString("userPw");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.example.back.exback.domain.address.QAddress(forProperty("address")) : null;
    }

}

