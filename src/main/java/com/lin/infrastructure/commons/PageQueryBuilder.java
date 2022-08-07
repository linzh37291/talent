package com.lin.infrastructure.commons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.function.Function;

import static org.springframework.data.relational.core.query.Query.query;

/**
 * 分页组件
 *
 * @param <T>
 * @author 林子豪
 */
public class PageQueryBuilder<T> {

    private final R2dbcEntityTemplate template;
    private final Class<T> clz;
    private Criteria criteria;
    private Pageable pageable;

    public PageQueryBuilder(R2dbcEntityTemplate template, Class<T> clz) {
        this.template = template;
        this.clz = clz;
    }

    public PageQueryBuilder<T> where(Criteria criteria) {
        this.criteria = criteria;
        return this;
    }

    public PageQueryBuilder<T> pageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public Mono<Page<T>> apply() {
        return Mono.zip(selectList(), selectCount())
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }

    public <R> Mono<Page<R>> apply(Function<List<T>, Mono<List<R>>> fn) {
        return Mono.zip(selectList().flatMap(fn), selectCount())
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }

    public <R> Mono<Page<R>> flatTuple(Function<Tuple2<List<T>, Long>, Mono<Tuple2<List<R>, Long>>> fn) {
        return Mono.zip(selectList(), selectCount())
                .flatMap(fn)
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }

    private Mono<List<T>> selectList() {
        return template.select(clz)
                .matching(query(criteria).with(pageable))
                .all()
                .collectList();
    }

    private Mono<Long> selectCount() {
        return template.select(clz)
                .matching(query(criteria))
                .count();
    }
}