package com.example.back.exback.domain.noticeboard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardCommon is a Querydsl query type for BoardCommon
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBoardCommon extends BeanPath<BoardCommon> {

    private static final long serialVersionUID = -176331931L;

    public static final QBoardCommon boardCommon = new QBoardCommon("boardCommon");

    public final StringPath content = createString("content");

    public final StringPath writer = createString("writer");

    public QBoardCommon(String variable) {
        super(BoardCommon.class, forVariable(variable));
    }

    public QBoardCommon(Path<? extends BoardCommon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardCommon(PathMetadata metadata) {
        super(BoardCommon.class, metadata);
    }

}

