/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */
package org.elasticsearch.xpack.sql.expression.function.scalar.datetime;

import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.expression.gen.pipeline.Pipe;
import org.elasticsearch.xpack.ql.expression.gen.processor.Processor;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;

import java.time.ZoneId;

public class DateTruncPipe extends BinaryDateTimePipe {

    public DateTruncPipe(Source source, Expression expression, Pipe left, Pipe right, ZoneId zoneId) {
        super(source, expression, left, right, zoneId);
    }

    @Override
    protected NodeInfo<DateTruncPipe> info() {
        return NodeInfo.create(this, DateTruncPipe::new, expression(), left(), right(), zoneId());
    }

    @Override
    protected DateTruncPipe replaceChildren(Pipe left, Pipe right) {
        return new DateTruncPipe(source(), expression(), left, right, zoneId());
    }

    @Override
    protected Processor makeProcessor(Processor left, Processor right, ZoneId zoneId) {
        return new DateTruncProcessor(left, right, zoneId);
    }
}
