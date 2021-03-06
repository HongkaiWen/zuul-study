package hello.filters.groovy

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext

import javax.servlet.http.HttpServletRequest

/**
 * Created by hongkai on 2016/10/22.
 */
class FirstGroovyFilter  extends ZuulFilter{

    @Override
    String filterType() {
        return "pre"
    }

    @Override
    int filterOrder() {
        return 1000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        HttpServletRequest req = RequestContext.currentContext.request as HttpServletRequest
        Iterator headerIt = req.getHeaderNames().iterator()
        while (headerIt.hasNext()) {
            String name = (String) headerIt.next()
            String value = req.getHeader(name)
            println("header: " + name + ":" + value)
        }
        return null
    }
}
