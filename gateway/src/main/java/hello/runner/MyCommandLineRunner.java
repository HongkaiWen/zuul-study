package hello.runner;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.filters.FilterRegistry;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;
import hello.filters.post.PostFilter;
import hello.filters.pre.SimpleFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * refer to https://github.com/liaokailin/springcloud/blob/master/myzuul/src/main/java/com/lkl/springcloud/zuul/filters/groovy/pre/PreRequest.groovy
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            MonitoringHelper.initMocks();
            initJavaFilters();
            FilterLoader.getInstance().setCompiler(new GroovyCompiler());
            try {
                FilterFileManager.setFilenameFilter(new GroovyFileFilter());
                FilterFileManager.init(10,"E:/code/github/zuul-study/gateway/src/main/java/hello/filters/groovy");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private void initJavaFilters() {
            final FilterRegistry r = FilterRegistry.instance();
            r.put("filter1", new SimpleFilter());
            r.put("filter2", new PostFilter());
        }

    }