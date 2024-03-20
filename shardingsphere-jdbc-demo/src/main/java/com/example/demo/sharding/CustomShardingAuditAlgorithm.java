/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.sharding;

import org.apache.shardingsphere.infra.binder.statement.SQLStatementContext;
import org.apache.shardingsphere.infra.executor.audit.exception.SQLAuditException;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.infra.metadata.database.ShardingSphereDatabase;
import org.apache.shardingsphere.infra.metadata.database.rule.ShardingSphereRuleMetaData;
import org.apache.shardingsphere.infra.metadata.user.Grantee;
import org.apache.shardingsphere.infra.util.exception.ShardingSpherePreconditions;
import org.apache.shardingsphere.sharding.route.engine.condition.engine.ShardingConditionEngine;
import org.apache.shardingsphere.sharding.rule.ShardingRule;
import org.apache.shardingsphere.sharding.spi.ShardingAuditAlgorithm;
import org.apache.shardingsphere.sql.parser.sql.common.statement.dml.DMLStatement;

import java.util.List;
import java.util.Objects;

/**
 * 自定义分片审计，规则如下：
 * 1、分片表shardingTable但是未指定路由信息，则抛出异常
 */
public final class CustomShardingAuditAlgorithm implements ShardingAuditAlgorithm {

    public CustomShardingAuditAlgorithm() {
    }

    public void check(SQLStatementContext<?> sqlStatementContext, List<Object> params, Grantee grantee, ShardingSphereRuleMetaData globalRuleMetaData, ShardingSphereDatabase database) {
        if (sqlStatementContext.getSqlStatement() instanceof DMLStatement) {
            ShardingRule rule = database.getRuleMetaData().getSingleRule(ShardingRule.class);
            if (!rule.isAllBroadcastTables(sqlStatementContext.getTablesContext().getTableNames())) {
                Objects.requireNonNull(rule);
                boolean audited = HintManager.isInstantiated() &&
                        (HintManager.getDatabaseShardingValues().size() > 0 || HintManager.getDataSourceName().isPresent());
                // 1、分片表shardingTable但是未指定路由信息，则抛出异常
                if (!audited && sqlStatementContext.getTablesContext().getTableNames().stream().anyMatch(rule::isShardingTable)) {
                    ShardingSpherePreconditions.checkState(!(new ShardingConditionEngine(globalRuleMetaData, database, rule)).createShardingConditions(sqlStatementContext, params).isEmpty(), () -> {
                        return new SQLAuditException("分库分表审计不通过,请务必解决!");
                    });
                }
            }
        }

    }

    @Override
    public String getType() {
        return "CUSTOM_SHARDING_CONDITIONS";
    }
}
