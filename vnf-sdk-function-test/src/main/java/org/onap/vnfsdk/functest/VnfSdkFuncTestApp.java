/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onap.vnfsdk.functest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.server.SimpleServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.onap.vnfsdk.functest.common.Config;
import org.onap.vnfsdk.functest.db.TaskMgrCaseTblDAO;
import org.onap.vnfsdk.functest.db.TaskMgrTaskTblDAO;
import org.onap.vnfsdk.functest.scriptmgr.ScriptManager;
import org.onap.vnfsdk.functest.taskmgr.TaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VnfSdkFuncTestApp extends Application<VnfSdkFuncTestAppConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VnfSdkFuncTestApp.class);
    private final HibernateBundle<VnfSdkFuncTestAppConfiguration> hibernateBundle =
            new ScanningHibernateBundle<VnfSdkFuncTestAppConfiguration>("org.onap.vnfsdk.functest.models") {
                @Override
                public DataSourceFactory getDataSourceFactory(VnfSdkFuncTestAppConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(String[] args) throws Exception {
        new VnfSdkFuncTestApp().run(args);
    }

    @Override
    public String getName() {
        return "ONAP-VNFSDK-FunctionTest";
    }

    @Override
    public void initialize(Bootstrap<VnfSdkFuncTestAppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(VnfSdkFuncTestAppConfiguration configuration, Environment environment) {
        LOGGER.info("Start to initialize vnfsdk function test.");
        environment.jersey().packages("org.onap.vnfsdk.functest.taskmgr");
        environment.jersey().register(MultiPartFeature.class);
        initSwaggerConfig(environment, configuration);
        Config.setConfigration(configuration);
        LOGGER.info("Initialize vnfsdk function test finished.");
    }

    private void initSwaggerConfig(Environment environment, VnfSdkFuncTestAppConfiguration configuration) {
        final TaskMgrTaskTblDAO taskDAO = new TaskMgrTaskTblDAO(hibernateBundle.getSessionFactory());
        final TaskMgrCaseTblDAO caseDAO = new TaskMgrCaseTblDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new ApiListingResource());
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        environment.jersey().register(new TaskManager(taskDAO, caseDAO, new ScriptManager(taskDAO, caseDAO)));

        BeanConfig config = new BeanConfig();
        config.setTitle("ONAP VnfSdk Functest Service rest API");
        config.setVersion("1.0.0");
        config.setResourcePackage("org.onap.vnfsdk.functest.taskmgr");

        SimpleServerFactory simpleServerFactory = (SimpleServerFactory) configuration.getServerFactory();
        String basePath = simpleServerFactory.getApplicationContextPath();
        String rootPath = simpleServerFactory.getJerseyRootPath().toString();
        rootPath = rootPath.substring(0, rootPath.indexOf("/*"));
        basePath =
                ("/").equals(rootPath) ? rootPath : (new StringBuilder()).append(basePath).append(rootPath).toString();
        config.setBasePath(basePath);
        config.setScan(true);
    }

}
